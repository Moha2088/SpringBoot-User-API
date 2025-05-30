package com.example.springbootstarter.Repositories.Models;

import com.example.springbootstarter.Repositories.DTOS.CreateUserDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID Id = UUID.randomUUID();
    public String Name;
    public String Email;

    public User(String name, String email) {
        Name = name;
        Email = email;
    }

    public User() { }
}
