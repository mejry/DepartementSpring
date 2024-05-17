package com.example.departementges.service;


import com.example.departementges.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user, String roleName);

    User getUserById(Long id);

    List<User> getAllUsers();

    User updateUser(Long id, User userDetails);

    void deleteUser(Long id);
}

