package com.electiondatabase.forms;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import com.electiondatabase.DatabaseService;
import com.electiondatabase.ElectionService;
import com.electiondatabase.ui.ButtonFactory;
import com.electiondatabase.ui.GridPaneFactory;
import com.electiondatabase.ui.HeaderLabelFactory;

public class MainMenu extends Application {
    private Stage primaryStage; // Might have to make this static, not sure yet
    private ElectionService electionService;
    private DatabaseService databaseService;

    @Override
    public void start(Stage primaryStageArg) throws Exception {
        initServices();
        this.primaryStage = primaryStageArg;
        // Create the registration form grid pane
        GridPane gridPane = new GridPaneFactory().getGridPane();
        // Add UI to the registration form grid pane
        addUI(gridPane);
        // Create a scene with registration form grid pane as the root node
        Scene scene = new Scene(gridPane, 1000, 500);
        // Set the scene in primary stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    protected void addUI(GridPane gridPane) {

        // Add Header
        Label headerLabel = new HeaderLabelFactory("Election Database").getHeaderLabel();
        gridPane.add(headerLabel, 0, 0, 2, 1);

        // Create buttons using ButtonGUI
        Button registerCandidatesButton = new ButtonFactory("Register Candidates").getButton();
        Button castVotersButton = new ButtonFactory("Cast Vote").getButton();
        Button viewResultsButton = new ButtonFactory("View Results/Progress").getButton();
        Button exitButton = new ButtonFactory("Exit").getButton();
        
        gridPane.add(registerCandidatesButton, 0, 1, 2, 1);
        gridPane.add(castVotersButton, 0, 2, 2, 1);
        gridPane.add(viewResultsButton, 0, 3, 2, 1);
        gridPane.add(exitButton, 0, 4, 2, 1);

        registerCandidatesButton.setOnAction(e -> openRegisterCandidatesForm());
        castVotersButton.setOnAction(e->openCastVoteForm());
        viewResultsButton.setOnAction(e->openResultsForm());
        exitButton.setOnAction(e -> {
            primaryStage.close();
            closeDatabase();
        });

    }

    private void openRegisterCandidatesForm(){
        RegisterCandidates registerCandidates = new RegisterCandidates(electionService);
        registerCandidates.initForm();
    }
    private void openCastVoteForm(){
        CastVoteForm castVoteForm = new CastVoteForm();
        castVoteForm.initForm();
    }

    private void openResultsForm(){
        ResultsForm resultsForm = new ResultsForm();
        resultsForm.initForm();
    }

    private void initServices() {
        Properties prop = new Properties();
        String connectionString;

        try (InputStream input = new FileInputStream("config.properties")) {
            prop.load(input);
            connectionString = prop.getProperty("mongo.connection.string");
        } catch (Exception ex) {
            ex.printStackTrace();
            // Handle the exception appropriately
            return;
        }

        DatabaseService databaseService = new DatabaseService(connectionString, "election");
        this.electionService = new ElectionService(databaseService);
        // Other initialization as needed
    }

    private void closeDatabase(){
        if (this.electionService != null) {
            try {
                this.databaseService.close();
            } catch (Exception e) {
                e.printStackTrace();
            }    
        }
    }
}
