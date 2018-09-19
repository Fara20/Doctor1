package com.example.farahreza.demo;

public class ClinicSignUpInformation {
    String name,email,phoneNo,location,password;

    public ClinicSignUpInformation() {
    }

    public ClinicSignUpInformation(String name, String email, String phoneNo, String location, String password) {
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
        this.location = location;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getLocation() {
        return location;
    }

    public String getPassword() {
        return password;
    }
}
