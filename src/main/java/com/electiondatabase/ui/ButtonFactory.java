package com.electiondatabase.ui;

import javafx.scene.control.Button;

public class ButtonFactory {
    private Button button;

    public ButtonFactory(String buttonText) {
        this.button = new Button(buttonText);
        setDefaultStyles();
    }

    private void setDefaultStyles() {
        button.setPrefHeight(40);
        button.setDefaultButton(true);
        button.setPrefWidth(200);
    }

    public Button getButton() {
        return button;
    }

}
