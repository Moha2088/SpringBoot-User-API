package com.example.springbootstarter.Models;

import com.example.springbootstarter.DTOS.Project.ProjectGetDto;
import com.example.springbootstarter.DTOS.User.UserGetDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Project")

public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(nullable = false, length = 50)
    private String Name;

    @Column(nullable = false, length = 30)
    private String Description;

    @Column(nullable = false)
    private Date From;

    @Column(nullable = false)
    private Date To;

    @OneToMany
    private List<User> Users;

    private Project() { }

    public Project(Long id, String name, String description, Date from, Date to) {
        this.Id = id;
        this.Name = name;
        this.Description = description;
        this.From = from;
        this.To = to;
    }

    public ProjectGetDto ToDto(){
        List<UserGetDto> users = new ArrayList<UserGetDto>();
        getUsers().forEach(user -> {
            users.add(new UserGetDto(user.getId(), user.getName(), user.getEmail()));
        });

        return new ProjectGetDto(getId(), getName(), getDescription(),getTo(), getFrom(), users);
    }

    // Getters and Setters

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Date getFrom() {
        return From;
    }

    public void setFrom(Date from) {
        From = from;
    }

    public Date getTo() {
        return To;
    }

    public void setTo(Date to) {
        To = to;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public List<User> getUsers() {
        return Users;
    }

    public void setUsers(List<User> users) {
        Users = users;
    }
}