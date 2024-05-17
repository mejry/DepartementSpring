package com.example.departementges.service.serviceImpl;

import com.example.departementges.models.Role;
import com.example.departementges.models.User;
import com.example.departementges.repository.RoleRepository;
import com.example.departementges.repository.UserRepository;
import com.example.departementges.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User createUser(User user, String roleName) {
        Role role = roleRepository.findByName(roleName);
        if (role == null) {
            role = new Role();
            role.setName(roleName);
            roleRepository.save(role);
        }
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        return userRepository.save(user);
    }
        @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Long id, User userDetails) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(userDetails.getName());
                    user.setPrename(userDetails.getPrename());
                    user.setEmail(userDetails.getEmail());
                    if (userDetails.getPassword() != null) {
                        user.setPassword(userDetails.getPassword());
                    }
                    user.setRoles(userDetails.getRoles());
                    return userRepository.save(user);
                })
                .orElse(null);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
