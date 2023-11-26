package com.electiondatabase.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

public class GridPaneFactory {
    private GridPane gridPane;

    public GridPaneFactory() {
        this.gridPane = new GridPane();
        setDefaultStyles();
    }

    private void setDefaultStyles() {
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(40, 40, 40, 40));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        // Add any other default settings here
    }

    public GridPane getGridPane() {
        return gridPane;
    }
}

