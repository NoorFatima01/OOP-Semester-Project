package com.electiondatabase.forms;

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


public class RegisterCandidates {
    private TextField nameField;
    private TextField regionField;
    private TextField partyField;

    private Stage primaryStage = new Stage();

    protected void addUIControls(GridPane gridPane) {
        // Add Header
        Label headerLabel = new HeaderLabelFactory("Registration Form For Candidate").getHeaderLabel();
        gridPane.add(headerLabel, 0,0,2,1);

        Label nameLabel = new Label("Full Name : ");
        gridPane.add(nameLabel, 0,1);
        nameField = new TextFieldFactory().getTextField();
        gridPane.add(nameField, 1,1);
        Label regionLabel = new Label("Region : ");
        gridPane.add(regionLabel, 0, 2);
        regionField = new TextFieldFactory().getTextField();
        gridPane.add(regionField, 1, 2);

        Label partyLabel = new Label("Political Party : ");
        gridPane.add(partyLabel, 0, 3);
        partyField = new TextFieldFactory().getTextField();
        
        gridPane.add(partyField, 1, 3);

        Button submitButton = new ButtonFactory("Register").getButton();
        gridPane.add(submitButton, 0, 4, 2, 1);
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setMargin(submitButton, new Insets(20, 0,20,0));

        Label alertLabel = new Label("");
        gridPane.add(alertLabel, 0, 6,2,1);
    }

    public void initForm(){
        GridPane gridPane = new GridPaneFactory().getGridPane();
        addUIControls(gridPane);
        Scene scene = new Scene(gridPane, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
}
