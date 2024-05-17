package com.example.departementges.controller;

import com.example.departementges.models.User;
import com.example.departementges.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/createStudent")
    public String showCreateStudentForm(Model model) {
        model.addAttribute("user", new User());
        return "createStudent";
    }

    @PostMapping("/createStudent")
    public String createStudent(@ModelAttribute User user) {
        userService.createUser(user, "STUDENT");
        return "redirect:/admin/users";
    }

    @GetMapping("/createTeacher")
    public String showCreateTeacherForm(Model model) {
        model.addAttribute("user", new User());
        return "createTeacher";
    }

    @PostMapping("/createTeacher")
    public String createTeacher(@ModelAttribute User user) {
        userService.createUser(user, "TEACHER");
        return "redirect:/admin/users";
    }

    @GetMapping("/user/{id}")
    public String getUserById(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        if (user != null) {
            model.addAttribute("user", user);
            return "userDetail";
        }
        return "notFound";
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "userList";
    }

    @GetMapping("/update/{id}")
    public String showUpdateUserForm(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        if (user != null) {
            model.addAttribute("user", user);
            return "updateUser";
        }
        return "notFound";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute User userDetails) {
        userService.updateUser(id, userDetails);
        return "redirect:/admin/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }
}
