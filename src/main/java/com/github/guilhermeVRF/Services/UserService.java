package com.github.guilhermeVRF.Services;

import java.util.List;

import com.github.guilhermeVRF.Models.User;
import com.github.guilhermeVRF.Repositories.UserRepository;

public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String name, String email) {
        return userRepository.createUser(name, email);
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public User getUser(String id) {
        return userRepository.getUser(id);
    }

    public User updateUser(String id, String name, String email) {
        return userRepository.updateUser(id, name, email);
    }

    public void deleteUser(String id) {
        userRepository.deleteUser(id);
    }
}
