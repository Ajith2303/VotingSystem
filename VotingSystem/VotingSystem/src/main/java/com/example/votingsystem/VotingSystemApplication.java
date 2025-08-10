package com.example.votingsystem;

import com.example.votingsystem.model.Candidate;
import com.example.votingsystem.repository.CandidateRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VotingSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(VotingSystemApplication.class, args);
    }

    // Preload some candidates if none exist
    @Bean
    CommandLineRunner initData(CandidateRepo candidateRepo) {
        return args -> {
            if (candidateRepo.count() == 0) {
                candidateRepo.save(new Candidate("Alice Johnson"));
                candidateRepo.save(new Candidate("Ravi Kumar"));
                candidateRepo.save(new Candidate("Meera Patel"));
            }
        };
    }
}
