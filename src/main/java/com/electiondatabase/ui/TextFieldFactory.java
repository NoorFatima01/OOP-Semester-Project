package com.electiondatabase.ui;

import javafx.scene.control.TextField;

public class TextFieldFactory {
    private TextField textField;

    public TextFieldFactory() {
        this.textField = new TextField();
        setDefaultStyles();
    }

    private void setDefaultStyles() {
        textField.setPrefHeight(30);
    }

    public TextField getTextField() {
        return textField;
    }
}
