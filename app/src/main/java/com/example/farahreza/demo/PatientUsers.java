package com.example.farahreza.demo;

public class PatientUsers {
    String name;
    String phone;
    String email;
    String password;
    String uid;


    public PatientUsers() {
    }

    public PatientUsers(String name, String phone, String email, String password, String uid) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUid() {
        return uid;
    }
}
