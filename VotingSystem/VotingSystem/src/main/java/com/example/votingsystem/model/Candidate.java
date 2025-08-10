package com.example.votingsystem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "candidate")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer votes = 0;

    public Candidate() {}

    public Candidate(String name) {
        this.name = name;
        this.votes = 0;
    }

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getVotes() { return votes; }
    public void setVotes(Integer votes) { this.votes = votes; }

    public void incrementVotes() {
        this.votes = (this.votes == null ? 1 : this.votes + 1);
    }
}
