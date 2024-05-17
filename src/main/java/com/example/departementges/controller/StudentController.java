package com.example.departementges.controller;

import com.example.departementges.models.Cours;
import com.example.departementges.service.CoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private CoursService coursService;

    @GetMapping("/cours/{id}")
    public String getCoursById(@PathVariable Long id, Model model) {
        Optional<Cours> cours = coursService.getCoursById(id);
        if (cours.isPresent()) {
            model.addAttribute("cours", cours.get());
            return "coursDetail";
        }
        return "notFound";
    }

    @GetMapping("/allcours")
    public String getAllCours(Model model) {
        List<Cours> coursList = coursService.getAllCours();
        model.addAttribute("coursList", coursList);
        return "CoursList";
    }
}
