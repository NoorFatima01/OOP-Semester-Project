package com.electiondatabase;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

public class ElectionService {
    private MongoCollection<Document> votersCollection;
    private MongoCollection<Document> candidatesCollection;

    public ElectionService(DatabaseService databaseService) {
        this.votersCollection = databaseService.getCollection("voters");
        this.candidatesCollection = databaseService.getCollection("candidates");
    }

    public void registerCandidate(Candidate candidate) {
        Document candidateDoc = new Document()
                .append("firstName", candidate.getFirstName())
                .append("lastName", candidate.getLastName())
                .append("region", candidate.getRegion())
                .append("party", candidate.getParty())
                .append("votes", 0); // Initialize votes to 0
        candidatesCollection.insertOne(candidateDoc);

        // Set the candidate id to the generated ObjectId
        candidate.setId(candidateDoc.getObjectId("_id"));
    }

    public void registerVoter(Voter voter) {
        // First find the candidate id by the name entered by the voter
        ObjectId candidateId = findCandidateIdByName(voter.getVotedCandidate()); // .getVotedCandidate() returns the
                                                                                 // full name of the candidate
        if (candidateId != null) {
            // Increment the vote count for the found candidate
            candidatesCollection.updateOne(Filters.eq("_id", candidateId), Updates.inc("votes", 1));

            Document voterDoc = new Document()
                    .append("firstName", voter.getFirstName())
                    .append("lastName", voter.getLastName())
                    .append("region", voter.getRegion())
                    .append("votedCandidateId", candidateId);
            votersCollection.insertOne(voterDoc);

            // Set the generated ObjectId to the voter
            voter.setId(voterDoc.getObjectId("_id"));
        } else {
            System.out.println("Voted candidate not found");
        }
    }

    private ObjectId findCandidateIdByName(String fullName) {
        String[] names = fullName.split("\\s+");
        if (names.length < 2)
            return null;

        String firstName = names[0];
        String lastName = names[1];

        Document candidate = candidatesCollection.find(Filters.and(
                Filters.eq("firstName", firstName),
                Filters.eq("lastName", lastName))).first();

        return candidate != null ? candidate.getObjectId("_id") : null;
    }

    public List<String> getAllCandidateNames() {
        List<String> candidateNames = new ArrayList<>();
        for (Document candidate : candidatesCollection.find()) {
            String fullName = candidate.getString("firstName") + " " + candidate.getString("lastName");
            candidateNames.add(fullName);
        }
        return candidateNames;
    }

    public List<String> calculateElectionWinner() {
        List<String> winners = new ArrayList<>();
        // Find the candidate with the highest number of votes
        FindIterable<Document> candidates = candidatesCollection.find().sort(Sorts.descending("votes"));
        int highestVotes = -1;

        for (Document candidate : candidates) {
            int votes = candidate.getInteger("votes");
            if (highestVotes == -1) {
                highestVotes = votes; // Set the highest votes based on the first (top-voted) candidate
            }

            // Check for tie situation
            if (votes == highestVotes) {
                // Add candidate to winners list if their vote count matches the highest votes
                String fullName = candidate.getString("firstName") + " " + candidate.getString("lastName");
                winners.add(fullName);
            } else {
                break; // Once a candidate with fewer votes is found, break the loop
            }
        }
        return winners;
    }
}