package com.example.farahreza.demo;

public class PatientUsers {
    String name;
    String phone;
    String email;
    String password;
   // String uid;



    public PatientUsers() {
    }

    public PatientUsers(String name, String phone, String email, String password) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;


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


}
