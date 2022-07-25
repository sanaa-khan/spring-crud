package com.example.springdemo.service;

import com.example.springdemo.repository.userRepository;
import com.example.springdemo.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private userRepository UserRepository;

    @Transactional(readOnly=true)
    public List<User> getAllUsers() {
        return UserRepository.findAll();
    }

    @Transactional(readOnly=true)
    public User getById(int id) {
        return UserRepository.findById(id);
    }

    @Transactional(readOnly=true)
    public User getByUsername(String username) {
        return UserRepository.findByUsername(username);
    }

    @Transactional(readOnly=true)
    public String getPasswordByUsername(String username) {
        return UserRepository.getPasswordByUsername(username);
    }

    @Transactional
    public int deleteById(int id) {
        return UserRepository.deleteById(id);
    }

    @Transactional
    public int insert(User user) {
        return UserRepository.insert(user);
    }

    @Transactional
    public int update(User user) {
        return UserRepository.update(user);
    }
}
