package com.example.demo.resolver;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Component
public class Query implements GraphQLQueryResolver {
 
	@Autowired
    private UserRepository userRepo;
  
    public Optional<User> user(String firstName) {
        return userRepo.findByFirstName(firstName);
    }
 
    public Iterable<User> findAllUsers() {
        return userRepo.findAll();
    }
 
}
