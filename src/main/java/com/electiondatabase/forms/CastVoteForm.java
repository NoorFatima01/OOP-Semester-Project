package com.electiondatabase.forms;


import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


import com.electiondatabase.ui.ButtonFactory;
import com.electiondatabase.ui.GridPaneFactory;
import com.electiondatabase.ui.HeaderLabelFactory;
import com.electiondatabase.ui.TextFieldFactory;

public class CastVoteForm{
    private TextField nameField;
    private TextField regionField;
    private TextField votedCandidateField;
    private Stage primaryStage = new Stage();;

    protected void addUIControls(GridPane gridPane) {

     // Add Header
        Label headerLabel = new HeaderLabelFactory("Voting Form").getHeaderLabel();
        gridPane.add(headerLabel, 0,0,2,1);

        Label candNamesLabel = new Label("Registered Candidates");
        candNamesLabel.setFont(Font.font("Arial",FontWeight.BOLD,12));
        gridPane.add(candNamesLabel,0,1 );
        candNamesLabel.setAlignment(Pos.CENTER_LEFT);
        GridPane.setHalignment(candNamesLabel, HPos.CENTER);

        // Add Name Label
        Label nameLabel = new Label("Full Name : ");
        gridPane.add(nameLabel, 0,3);
        // Add Name Text Field
        nameField = new TextFieldFactory().getTextField();
        gridPane.add(nameField, 1,3);
        // Add Region Label
        Label regionLabel = new Label("Region : ");
        gridPane.add(regionLabel, 0, 4);
        // Add Region Text Field
        regionField = new TextFieldFactory().getTextField();
        gridPane.add(regionField, 1, 4);
        // Add Party Label
        Label votedCandidateLabel = new Label("Chosen Candidate : ");
        gridPane.add(votedCandidateLabel, 0, 5);
        // Add Party Field
        votedCandidateField = new TextFieldFactory().getTextField();
        gridPane.add(votedCandidateField, 1, 5);

        Button casteButton = new ButtonFactory("Caste Vote").getButton();
        gridPane.add(casteButton, 1, 6);
        GridPane.setHalignment(casteButton, HPos.RIGHT);
        GridPane.setMargin(casteButton, new Insets(20, 0, 0, 0));
        casteButton.setOnAction(null);
    }

    public void initForm(){
        GridPane gridPane = new GridPaneFactory().getGridPane();
        addUIControls(gridPane);
        Scene scene = new Scene(gridPane, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
}
