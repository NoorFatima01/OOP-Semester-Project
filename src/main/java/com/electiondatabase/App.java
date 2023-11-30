package com.electiondatabase;

import javafx.application.Application;

import com.electiondatabase.forms.MainMenu;

public class App{
    public static void main(String[] args) {
        String workingDir = System.getProperty("user.dir");
        System.out.println("Current working directory: " + workingDir);
        try {

            Application.launch(MainMenu.class, args);

        } catch (Exception e) {
            e.printStackTrace();
            
        }
}
}