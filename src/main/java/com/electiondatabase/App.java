package com.electiondatabase;

import javafx.application.Application;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class App {
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
