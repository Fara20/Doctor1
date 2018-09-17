package com.example.farahreza.demo;

public class Users {
    String type;
    String uid;

    public Users(String type, String uid) {
        this.type = type;
        this.uid = uid;
    }

    public Users() {
    }

    public String getType() {
        return type;
    }

    public String getUid() {
        return uid;
    }
}
