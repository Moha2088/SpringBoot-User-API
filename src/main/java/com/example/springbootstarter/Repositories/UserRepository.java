package com.example.springbootstarter.Repositories;

import com.example.springbootstarter.Models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByEmail(String Email);
    
    Optional<User> findByName(String Name);
}