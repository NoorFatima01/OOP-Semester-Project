package com.electiondatabase;

public class Candidate extends User {
    private int votes;
    private String party;

    public Candidate(int id, String firstName, String lastName, String region, String party) {
        super(id, firstName, lastName, region);
        this.party = party;
    }

    public int getVotes() {
        return votes;
    }

    public String getParty() {
        return party;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public void setParty(String party) {
        this.party = party;
    }

}
