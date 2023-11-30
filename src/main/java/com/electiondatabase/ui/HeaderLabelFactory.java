package com.electiondatabase.ui;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class HeaderLabelFactory {
    private Label headerLabel;

    public HeaderLabelFactory(String text) {
        this.headerLabel = new Label(text);
        setDefaultStyles();
    }

    private void setDefaultStyles() {
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0, 20, 0));
        // Add any other default settings here
    }

    public Label getHeaderLabel() {
        return headerLabel;
    }
}
