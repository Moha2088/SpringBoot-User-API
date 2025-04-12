package com.example.springbootstarter.Repositories.Models;

import java.util.UUID;


public class User {

    public UUID Id = UUID.randomUUID();
    public String Name;
    public String Email;

    public User(String name, String email) {
        Name = name;
        Email = email;
    }

    public User() { }
}
