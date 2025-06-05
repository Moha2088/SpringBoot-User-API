package com.example.springbootstarter.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import com.example.springbootstarter.DTOS.User.UserGetDto;

@Entity
@Table(name = "User", indexes = {
        @Index(name = "idx_name", columnList = "Name"),
        @Index(name = "idx_email", columnList = "Email")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

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