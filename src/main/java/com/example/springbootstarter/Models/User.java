package com.example.springbootstarter.Models;

import com.example.springbootstarter.DTOS.UserGetDto;

import javax.persistence.*;

@Entity
@Table(name = "User", indexes = {
        @Index(name = "idx_name", columnList = "Name"),
        @Index(name = "idx_email", columnList = "Email")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long Id;

    @Column(length = 20, nullable = false)
    private String Name;

    @Column(length = 30, nullable = false)
    private String Email;

    public User(String name, String email) {
        Name = name;
        Email = email;
    }

    private User() { }

    // Getters and Setters

    public Long getId() {
        return Id;
    }

    public String getEmail() {
        return Email;
    }

    public String getName() {
        return Name;
    }

    public void setId(Long id) {
        Id = id;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setName(String name) {
        Name = name;
    }

    public UserGetDto ToDto(){
        return new UserGetDto(this.Id, this.Name, this.Email);
    }
}