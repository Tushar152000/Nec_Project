package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Admin {

    @Id
    private String username;
    private String password;

    public Admin() {
    }

    // Parameterized constructor
    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters and setters

    @Override
    public String toString() {
        return "Admin [username=" + username + ", password=" + password + "]";
    }

    // Correct implementation of getPassword method
    public String getPassword() {
        return password;
    }
}