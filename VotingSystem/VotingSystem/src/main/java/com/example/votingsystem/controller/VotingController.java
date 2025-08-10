package com.example.votingsystem.controller;

import com.example.votingsystem.model.Candidate;
import com.example.votingsystem.model.Citizen;
import com.example.votingsystem.repository.CandidateRepo;
import com.example.votingsystem.repository.CitizenRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class VotingController {

    private final CitizenRepo citizenRepo;
    private final CandidateRepo candidateRepo;

    public VotingController(CitizenRepo citizenRepo, CandidateRepo candidateRepo) {
        this.citizenRepo = citizenRepo;
        this.candidateRepo = candidateRepo;
    }

    // show login
    @GetMapping({"/", "/vote"})
    public String votePage() {
        return "vote";
    }

    // process login
    @PostMapping("/doLogin")
    public String doLogin(@RequestParam("name") String name, HttpSession session, Model model) {
        if (name == null || name.trim().isEmpty()) {
            model.addAttribute("error", "Please enter a valid name");
            return "vote";
        }

        String cleanName = name.trim();
        Citizen citizen = citizenRepo.findByNameIgnoreCase(cleanName).orElseGet(() -> {
            Citizen newC = new Citizen(cleanName);
            return citizenRepo.save(newC);
        });

        if (Boolean.TRUE.equals(citizen.getHasVoted())) {
            // Already voted
            model.addAttribute("citizenName", citizen.getName());
            return "alreadyVoted";
        }

        // store id in session
        session.setAttribute("citizenId", citizen.getId());
        return "redirect:/performVoted";
    }

    // show candidates
    @GetMapping("/performVoted")
    public String performVoted(Model model) {
        List<Candidate> candidates = candidateRepo.findAll();
        model.addAttribute("candidates", candidates);
        return "performVoted";
    }

    // vote for candidate
    @GetMapping("/voteFor")
    public String voteFor(@RequestParam("id") Long candidateId, HttpSession session, Model model) {
        Object idObj = session.getAttribute("citizenId");
        if (idObj == null) {
            return "redirect:/vote";
        }

        Long citizenId = (Long) idObj;
        Optional<Citizen> oc = citizenRepo.findById(citizenId);
        if (oc.isEmpty()) return "redirect:/vote";

        Citizen citizen = oc.get();
        if (Boolean.TRUE.equals(citizen.getHasVoted())) {
            model.addAttribute("citizenName", citizen.getName());
            return "alreadyVoted";
        }

        Optional<Candidate> candOpt = candidateRepo.findById(candidateId);
        if (candOpt.isEmpty()) {
            model.addAttribute("error", "Candidate not found");
            return "performVoted";
        }

        Candidate candidate = candOpt.get();
        candidate.incrementVotes();
        candidateRepo.save(candidate);

        citizen.setHasVoted(true);
        citizenRepo.save(citizen);

        // clear session if you want (optional)
        session.removeAttribute("citizenId");

        model.addAttribute("candidateName", candidate.getName());
        return "thankyou";
    }

    // results
    @GetMapping("/results")
    public String results(Model model) {
        List<Candidate> candidates = candidateRepo.findAll();
        model.addAttribute("candidates", candidates);
        return "results";
    }
}
