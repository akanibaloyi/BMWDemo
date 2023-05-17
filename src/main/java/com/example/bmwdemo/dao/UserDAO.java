package com.example.bmwdemo.dao;

import com.example.bmwdemo.repository.UserRepository;
import com.example.bmwdemo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDAO {
    @Autowired
    UserRepository userRepository;
    public User retrieveUser(String username){
        User user= userRepository.findByUsername(username);
        return user;
    }
}
