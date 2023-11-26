package com.electiondatabase;

import javafx.application.Application;

import com.electiondatabase.forms.MainMenu;
import com.mongodb.client.MongoCollection;
import org.bson.Document;


import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class App{
    public static void main(String[] args) {
        Properties prop = new Properties();
        String connectionString;

        try (InputStream input = new FileInputStream("config.properties")) {
            // Load properties file
            prop.load(input);
            // Get the connection string from properties file
            connectionString = prop.getProperty("mongo.connection.string");
        } catch (Exception ex) {
            ex.printStackTrace();
            return; // Stop the execution if the connection string can't be loaded
        }

        DatabaseService databaseService = new DatabaseService(connectionString, "election");

        try {

            MongoCollection<Document> votersCollection = databaseService.getCollection("voters");
            MongoCollection<Document> candidatesCollection = databaseService.getCollection("candidates");

            Application.launch(MainMenu.class, args);

        } catch (Exception e) {
            e.printStackTrace();
            
        } finally {
            try {
                databaseService.close(); // Close the database connection here
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
}