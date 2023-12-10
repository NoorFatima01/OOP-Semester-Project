package com.electiondatabase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ElectionService {
    private FileService votersCollectionFileService, candidatesColletionFileService;

    public ElectionService(FileService votersCollectionFileService, FileService candidatesColletionFileService) {
        this.votersCollectionFileService = votersCollectionFileService;
        this.candidatesColletionFileService = candidatesColletionFileService;
    }

    public void registerCandidate(Candidate candidate) {
        String candidateData = candidate.getFirstName() + "," + candidate.getLastName() + "," + candidate.getRegion()
                + "," + candidate.getParty() + ",0";
        try {
            candidatesColletionFileService.writeToFile(candidateData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void registerVoter(Voter voter) {
        incrementCandidateVotes(voter.getVotedCandidate());

        String voterData = voter.getFirstName() + "," + voter.getLastName() + "," + voter.getRegion() + ","
                + voter.getVotedCandidate();
        try {
            votersCollectionFileService.writeToFile(voterData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getAllCandidateNames() {
        List<String> candidateNames = new ArrayList<>();
        try {
            List<String> lines = candidatesColletionFileService.readFromFile();
            for (String line : lines) {
                String[] details = line.split(",");
                String name = details[0] + " " + details[1];
                candidateNames.add(name);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return candidateNames;
    }

    private void incrementCandidateVotes(String candidateName) {
        try {
            List<String> lines = candidatesColletionFileService.readFromFile();
            
            for (String line : lines) {
                String[] details = line.split(",");
                String currentName = details[0] + " " + details[1];
                if (currentName.equals(candidateName)) {
                    int votes = Integer.parseInt(details[4]);
                    votes++;
                    String newLine = details[0] + "," + details[1] + "," + details[2] + "," + details[3] + "," + votes;
                    candidatesColletionFileService.updateLine(line, newLine);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> calculateElectionWinner() {
        int highestVotes = 0;
        List<String> winners = new ArrayList<>();
        try {
            List<String> lines = candidatesColletionFileService.readFromFile();
            // First loop: Find the highest number of votes
            for (String line : lines) {
                String[] details = line.split(",");
                int votes = Integer.parseInt(details[4]);
                if (votes > highestVotes) {
                    highestVotes = votes;
                }
            }
            // Second loop: Find all candidates with the highest number of votes
            for (String line : lines) {
                String[] details = line.split(",");
                int votes = Integer.parseInt(details[4]);
                if (votes == highestVotes) {
                    String name = details[0] + " " + details[1];
                    winners.add(name);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return winners;
    }
}