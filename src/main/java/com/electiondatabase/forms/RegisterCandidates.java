package com.electiondatabase.forms;

import com.electiondatabase.ElectionService;
import com.electiondatabase.ui.ButtonFactory;
import com.electiondatabase.ui.GridPaneFactory;
import com.electiondatabase.ui.HeaderLabelFactory;
import com.electiondatabase.ui.TextFieldFactory;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import com.electiondatabase.Candidate; // Add this import statement


public class RegisterCandidates {
    private TextField firstNameField;
    private TextField lastNameField;

    private TextField regionField;
    private TextField partyField;
    private ElectionService electionService;

    public RegisterCandidates(ElectionService electionService) {
        this.electionService = electionService;
        // rest of your constructor code
    }

    private Stage primaryStage = new Stage();

    protected void addUIControls(GridPane gridPane) {
        // Add Header
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
        lastNameField = new TextFieldFactory().getTextField(); // Corrected variable name
        gridPane.add(lastNameField, 1, 2);
    
        // Region
        Label regionLabel = new Label("Region : ");
        gridPane.add(regionLabel, 0, 3); // Adjusted row index
        regionField = new TextFieldFactory().getTextField();
        gridPane.add(regionField, 1, 3); // Adjusted row index
    
        // Political Party
        Label partyLabel = new Label("Political Party : ");
        gridPane.add(partyLabel, 0, 4); // Adjusted row index
        partyField = new TextFieldFactory().getTextField();
        gridPane.add(partyField, 1, 4); // Adjusted row index
    
        // Submit Button
        Button submitButton = new ButtonFactory("Register").getButton();
        gridPane.add(submitButton, 0, 5, 2, 1); // Adjusted row index
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setMargin(submitButton, new Insets(20, 0, 20, 0));

        submitButton.setOnAction(actionEvent -> 
        {
            Register();
            System.out.println("Candidate Registered");
        }); // Corrected method name
    
        // Alert Label
        Label alertLabel = new Label("");
        gridPane.add(alertLabel, 0, 6, 2, 1); // Adjusted row index
    }
    

    public void initForm(){
        GridPane gridPane = new GridPaneFactory().getGridPane();
        addUIControls(gridPane);
        Scene scene = new Scene(gridPane, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void Register(){
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String region = regionField.getText();
        String party = partyField.getText();
        party = partyField.getText();
        electionService.registerCandidate(new Candidate(firstName, lastName, region, party));
    }
}
