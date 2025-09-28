package com.github.guilhermeVRF.Repositories;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.github.guilhermeVRF.Models.User;
import com.mongodb.client.MongoCollection;

public class UserRepository {
    private MongoCollection<Document> userCollection;

    public UserRepository(MongoCollection<Document> userCollection) {
        this.userCollection = userCollection;
    }

    public User createUser(String name, String email) {
        Document user = new Document("name", name)
                .append("email", email);

        userCollection.insertOne(user);
        return new User(user.getObjectId("_id"), name, email);
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        for (Document doc : userCollection.find()) {
            users.add(new User(doc.getObjectId("_id"), doc.getString("name"), doc.getString("email")));
        }

        return users;
    }

    public User getUser(String id) {
        ObjectId objectId = new ObjectId(id);
        Document doc = userCollection.find(new Document("_id", objectId)).first();
        if (doc != null) {
            return new User(doc.getObjectId("_id"), doc.getString("name"), doc.getString("email"));
        }
        return null;
    }

    public User updateUser(String id, String name, String email) {
        Document updatedUser = new Document("name", name)
                .append("email", email);

        ObjectId objectId = new ObjectId(id);

        userCollection.updateOne(new Document("_id", objectId), new Document("$set", updatedUser));
        return getUser(id);
    }

    public void deleteUser(String id) {
        ObjectId objectId = new ObjectId(id);
        userCollection.deleteOne(new Document("_id", objectId));
    }
}
