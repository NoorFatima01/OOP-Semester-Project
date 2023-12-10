package com.electiondatabase.forms;

import com.electiondatabase.ElectionService;
import com.electiondatabase.ui.ButtonFactory;
import com.electiondatabase.ui.HeaderLabelFactory;
import com.electiondatabase.ui.TextFieldFactory;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import com.electiondatabase.Candidate;

public class RegisterCandidates extends Form {
    private TextField firstNameField;
    private TextField lastNameField;
    private TextField regionField;
    private TextField partyField;

    public RegisterCandidates(ElectionService electionService) {
        super(electionService);
        // is there need to initialize the text fields here?
    }

    public void addUIControls(GridPane gridPane) {
        // Header
        Label headerLabel = new HeaderLabelFactory("Registration Form For Candidate").getHeaderLabel();
        gridPane.add(headerLabel, 0, 0, 2, 1);

        // First Name
        Label firstNameLabel = new Label("First Name : ");
        gridPane.add(firstNameLabel, 0, 1);
        firstNameField = new TextFieldFactory().getTextField();
        gridPane.add(firstNameField, 1, 1);

        // Last Name
        Label lastNameLabel = new Label("Last Name : ");
        gridPane.add(lastNameLabel, 0, 2);
        lastNameField = new TextFieldFactory().getTextField();
        gridPane.add(lastNameField, 1, 2);

        // Region
        Label regionLabel = new Label("Region : ");
        gridPane.add(regionLabel, 0, 3);
        regionField = new TextFieldFactory().getTextField();
        gridPane.add(regionField, 1, 3);

        // Political Party
        Label partyLabel = new Label("Political Party : ");
        gridPane.add(partyLabel, 0, 4);
        partyField = new TextFieldFactory().getTextField();
        gridPane.add(partyField, 1, 4);

        // Submit Button
        Button submitButton = new ButtonFactory("Register").getButton();
        gridPane.add(submitButton, 0, 5, 2, 1);
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setMargin(submitButton, new Insets(20, 0, 20, 0));

        submitButton.setOnAction(actionEvent -> {
            Register(gridPane);
            System.out.println("Candidate Registered");
        });
    }

    public void Register(GridPane gridPane) {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String region = regionField.getText();
        String party = partyField.getText();
        party = partyField.getText();
        electionService.registerCandidate(new Candidate(firstName, lastName, region, party));

        showAlert(Alert.AlertType.INFORMATION, gridPane.getScene().getWindow(), "Success!",
                    "Candidate registered successfully");
        firstNameField.clear();
        lastNameField.clear();
        regionField.clear();
        partyField.clear();
    }
}
