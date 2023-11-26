package com.electiondatabase.forms;


import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import com.electiondatabase.ElectionService;
import com.electiondatabase.Voter;
import com.electiondatabase.ui.ButtonFactory;
import com.electiondatabase.ui.GridPaneFactory;
import com.electiondatabase.ui.HeaderLabelFactory;
import com.electiondatabase.ui.TextFieldFactory;

public class CastVoteForm{
    private TextField firstNameField;
    private TextField lastNameField;
    private TextField regionField;
    private TextField votedCandidateField;
    private ElectionService electionService;

    private Stage primaryStage = new Stage();

    public CastVoteForm(ElectionService electionService) {
        this.electionService = electionService;
        // rest of your constructor code
    }

    protected void addUIControls(GridPane gridPane) {
        // Add Header
        Label headerLabel = new HeaderLabelFactory("Voting Form").getHeaderLabel();
        gridPane.add(headerLabel, 0, 0, 2, 1);
    
        Label candNamesLabel = new Label("Registered Candidates");
        candNamesLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        gridPane.add(candNamesLabel, 0, 1);
        GridPane.setHalignment(candNamesLabel, HPos.CENTER);
    
        // Add First Name Label
        Label firstNameLabel = new Label("First Name : ");
        gridPane.add(firstNameLabel, 0, 2);
        // Add First Name Text Field
        firstNameField = new TextFieldFactory().getTextField();
        gridPane.add(firstNameField, 1, 2);
    
        // Add Last Name Label
        Label lastNameLabel = new Label("Last Name : ");
        gridPane.add(lastNameLabel, 0, 3);
        // Add Last Name Text Field
        lastNameField = new TextFieldFactory().getTextField();
        gridPane.add(lastNameField, 1, 3);
    
        // Add Region Label
        Label regionLabel = new Label("Region : ");
        gridPane.add(regionLabel, 0, 4);
        // Add Region Text Field
        regionField = new TextFieldFactory().getTextField();
        gridPane.add(regionField, 1, 4);
    
        // Add Chosen Candidate Label
        Label votedCandidateLabel = new Label("Chosen Candidate : ");
        gridPane.add(votedCandidateLabel, 0, 5);
        // Add Chosen Candidate Field
        votedCandidateField = new TextFieldFactory().getTextField();
        gridPane.add(votedCandidateField, 1, 5);
    
        // Add Cast Vote Button
        Button casteButton = new ButtonFactory("Cast Vote").getButton();
        gridPane.add(casteButton, 1, 6);
        GridPane.setHalignment(casteButton, HPos.RIGHT);
        GridPane.setMargin(casteButton, new Insets(20, 0, 0, 0));
        casteButton.setOnAction(e->{
            RegisterVoterAndCasteVote();
            System.out.println("Voter Registered and vote casted");
        });
    }
    

    public void initForm(){
        GridPane gridPane = new GridPaneFactory().getGridPane();
        addUIControls(gridPane);
        Scene scene = new Scene(gridPane, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void RegisterVoterAndCasteVote(){
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String region = regionField.getText();
        String votedCandidate = votedCandidateField.getText();
        electionService.registerVoter(new Voter(firstName, lastName, region, votedCandidate));
    }

}
