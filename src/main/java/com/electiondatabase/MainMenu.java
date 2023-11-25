package com.electiondatabase;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MainMenu extends Application {
    private Stage primaryStage; // Might have to make this static, not sure yet

    @Override
    public void start(Stage primaryStageArg) throws Exception {
        this.primaryStage = primaryStageArg;
        // Create the registration form grid pane
        GridPane gridPane = createFormPane();
        // Add UI controls to the registration form grid pane
        addUIControls(gridPane);
        // Create a scene with registration form grid pane as the root node
        Scene scene = new Scene(gridPane, 1000, 500);
        // Set the scene in primary stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    protected GridPane createFormPane() {
        GridPane gridPane = new GridPane();
        // Position the pane at the center of the screen, both vertically and
        // horizontally
        gridPane.setAlignment(Pos.CENTER);
        // Set a padding of 20px on each side
        gridPane.setPadding(new Insets(40, 40, 40, 40));
        // Set the horizontal gap between columns
        gridPane.setHgap(15);
        // Set the vertical gap between rows
        gridPane.setVgap(15);
        // Add Column Constraints
        // columnOneConstraints will be applied to all the nodes placed in column one.
        ColumnConstraints columnOneConstraints = new ColumnConstraints(200, 200, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.RIGHT);
        // columnTwoConstraints will be applied to all the nodes placed in column two.
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(300, 300, Double.MAX_VALUE);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);
        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);
        return gridPane;
    }

    protected void addUIControls(GridPane gridPane) {

        // Add Header
        Label headerLabel = new Label("Election Database");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0, 0, 2, 1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0, 20, 0));

        // Add Menu Buttons

        // Register Candidates Button
        Button registerCandidatesButton = new Button("Register Candidates");
        registerCandidatesButton.setPrefHeight(40);
        registerCandidatesButton.setDefaultButton(true);
        registerCandidatesButton.setPrefWidth(200);
        gridPane.add(registerCandidatesButton, 0, 1, 2, 1);
        GridPane.setHalignment(registerCandidatesButton, HPos.CENTER);
        GridPane.setMargin(registerCandidatesButton, new Insets(20, 0, 20, 0));

        // Cast Voters Button
        Button castVotersButton = new Button("Cast Vote");
        castVotersButton.setPrefHeight(40);
        castVotersButton.setDefaultButton(true);
        castVotersButton.setPrefWidth(200);
        gridPane.add(castVotersButton, 0, 2, 2, 1);
        GridPane.setHalignment(castVotersButton, HPos.CENTER);
        GridPane.setMargin(castVotersButton, new Insets(20, 0, 20, 0));

        // View Results Button
        Button viewResultsButton = new Button("View Results/Progress");
        viewResultsButton.setPrefHeight(40);
        viewResultsButton.setDefaultButton(true);
        viewResultsButton.setPrefWidth(200);
        gridPane.add(viewResultsButton, 0, 3, 2, 1);
        GridPane.setHalignment(viewResultsButton, HPos.CENTER);
        GridPane.setMargin(viewResultsButton, new Insets(20, 0, 20, 0));

        // Exit Button
        Button exitButton = new Button("Exit");
        exitButton.setPrefHeight(40);
        exitButton.setDefaultButton(true);
        exitButton.setPrefWidth(200);
        gridPane.add(exitButton, 0, 4, 2, 1);
        GridPane.setHalignment(exitButton, HPos.CENTER);
        GridPane.setMargin(exitButton, new Insets(20, 0, 20, 0));
    }
}
