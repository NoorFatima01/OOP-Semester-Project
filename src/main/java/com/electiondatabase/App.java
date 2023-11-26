package com.electiondatabase;

import javafx.application.Application;

import com.electiondatabase.forms.MainMenu;


import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class App{
    public static void main(String[] args) {
        try {

            Application.launch(MainMenu.class, args);

        } catch (Exception e) {
            e.printStackTrace();
            
        }
}
}