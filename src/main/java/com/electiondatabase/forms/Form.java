package com.electiondatabase.forms;

import com.electiondatabase.ElectionService;
import com.electiondatabase.ui.GridPaneFactory;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;

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

    protected void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}
