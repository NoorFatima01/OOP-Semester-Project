package com.electiondatabase.forms;

import java.util.List;

import com.electiondatabase.ElectionService;
import com.electiondatabase.ui.ButtonFactory;
import com.electiondatabase.ui.HeaderLabelFactory;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

public class ResultsForm extends Form{

    public ResultsForm(ElectionService electionService){
        super(electionService);
    }

    public void addUIControls(GridPane gridPane){
         // Add Header
        Label headerLabel = new HeaderLabelFactory("Candidates Progress").getHeaderLabel();
        gridPane.add(headerLabel, 0, 0, 2, 1);

        Button winnerButton = new ButtonFactory("Announce Winner").getButton();
        gridPane.add(winnerButton, 0, 7, 2, 1);
        GridPane.setHalignment(winnerButton, HPos.CENTER);
        GridPane.setMargin(winnerButton, new Insets(20, 0, 20, 0));
        winnerButton.setOnAction(e -> AnnounceWinner(gridPane));
    }

    private void AnnounceWinner(GridPane gridPane) {
        List<String> winners = electionService.calculateElectionWinner();
        StringBuilder winnerText = new StringBuilder();
    
        for (String s : winners) {
            winnerText.append(s).append("\n");
        }
    
        TextArea winnerTextArea = new TextArea(winnerText.toString());
        winnerTextArea.setEditable(false);
        winnerTextArea.setWrapText(true);
        gridPane.add(winnerTextArea, 0, 8, 2, 1);
        GridPane.setHalignment(winnerTextArea, HPos.CENTER);
        GridPane.setMargin(winnerTextArea, new Insets(20, 0, 20, 0));
    }
}
