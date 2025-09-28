package com.github.guilhermeVRF.Models;

import org.bson.types.ObjectId;

public class User {
    private ObjectId id;
    private String name;
    private String email;

    public User() {}

    public User(ObjectId id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return this.id.toHexString();
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }
}
