package com.electiondatabase.forms;

import java.util.List;

import com.electiondatabase.ElectionService;
import com.electiondatabase.ui.ButtonFactory;
import com.electiondatabase.ui.GridPaneFactory;
import com.electiondatabase.ui.HeaderLabelFactory;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;



public class ResultsForm {

    private Stage primaryStage = new Stage();
    ElectionService electionService;

    public ResultsForm(ElectionService electionService){
        this.electionService = electionService;
    }



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
        winnerButton.setOnAction(e -> AnnounceWinner(gridPane));



    }

    public void initForm(){
        GridPane gridPane = new GridPaneFactory().getGridPane();
        addUIControls(gridPane);
        Scene scene = new Scene(gridPane, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

    private void AnnounceWinner(GridPane gridPane) {
        List<String> winners = electionService.calculateElectionWinner();
        StringBuilder winnerText = new StringBuilder();
    
        for (String s : winners) {
            winnerText.append(s).append("\n");
        }
    
        TextArea winnerTextArea = new TextArea(winnerText.toString());
        winnerTextArea.setEditable(false); // Make the TextArea non-editable
        winnerTextArea.setWrapText(true); // Enable text wrapping
    
        // Add winnerTextArea to the provided GridPane, just below the Announce Winner button
        // Assuming the button is at row 7, we place the TextArea at row 8
        gridPane.add(winnerTextArea, 0, 8, 2, 1); // Spanning two columns
        GridPane.setHalignment(winnerTextArea, HPos.CENTER);
        GridPane.setMargin(winnerTextArea, new Insets(20, 0, 20, 0));
    }
    
    
}
