package com.electiondatabase;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class DatabaseService implements AutoCloseable {
    private MongoClient mongoClient;
    private MongoDatabase database;

    public DatabaseService(String connectionString, String dbName) {
        this.mongoClient = MongoClients.create(connectionString);
        this.database = mongoClient.getDatabase(dbName);
        System.out.println("Connected to the database successfully");
    }

    @Override
    public void close() throws Exception {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }

    public MongoDatabase getDatabase() {
        return database;
    }
    public void setDatabase(MongoDatabase database) {
        this.database = database;
    }

    public MongoCollection<Document> getCollection(String name) {
        return database.getCollection(name);
    }

}
