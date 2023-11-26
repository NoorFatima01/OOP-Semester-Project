package com.electiondatabase.forms;

import com.electiondatabase.ElectionService;
import com.electiondatabase.ui.GridPaneFactory;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public abstract class Form {
    protected ElectionService electionService;
    protected Stage primaryStage = new Stage();

    public Form(ElectionService electionService) {
        this.electionService = electionService;
    }

    public abstract void addUIControls(GridPane gridPane);

    public void initForm() {
        // Common implementation of initForm
        GridPane gridPane = new GridPaneFactory().getGridPane();
        addUIControls(gridPane);
        Scene scene = new Scene(gridPane, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
