package com.electiondatabase.forms;

import com.electiondatabase.ui.ButtonFactory;
import com.electiondatabase.ui.GridPaneFactory;
import com.electiondatabase.ui.HeaderLabelFactory;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;



public class ResultsForm {

    private Stage primaryStage = new Stage();



    protected void addUIControls(GridPane gridPane){
         // Add Header
        Label headerLabel = new HeaderLabelFactory("Candidates Progress").getHeaderLabel();
        gridPane.add(headerLabel, 0, 0, 2, 1);
         
        Label winnerLabel = new Label("");
        winnerLabel.setFont(Font.font("Arial",FontWeight.BOLD,15));
        gridPane.add(winnerLabel,0,4,2,1);

        Button winnerButton = new ButtonFactory("Announce Winner").getButton();
        gridPane.add(winnerButton, 0, 7, 2, 1);
        GridPane.setHalignment(winnerButton, HPos.CENTER);
        GridPane.setMargin(winnerButton, new Insets(20, 0, 20, 0));



    }

    public void initForm(){
        GridPane gridPane = new GridPaneFactory().getGridPane();
        addUIControls(gridPane);
        Scene scene = new Scene(gridPane, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
}
