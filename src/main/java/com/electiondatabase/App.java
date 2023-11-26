package com.electiondatabase;

import javafx.application.Application;

import com.electiondatabase.forms.MainMenu;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document; // Import the Document class

public class App{
    public static void main(String[] args) {
        String connectionString = "mongodb://localhost:27017";

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("election");
            System.out.println("Connected to the database successfully");

            MongoCollection<Document> votersCollection = database.getCollection("voters");
            MongoCollection<Document> candidatesCollection = database.getCollection("candidates");

            Application.launch(MainMenu.class, args);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
