package com.example.departementges.controller;

import com.example.departementges.models.Abscence;
import com.example.departementges.models.AbscenceDTO;
import com.example.departementges.models.Cours;
import com.example.departementges.service.AbscenceService;
import com.example.departementges.service.CoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private CoursService coursService;
    @Autowired
    private AbscenceService abscenceService;

    @GetMapping("/absence/{id}")
    public String getAbscenceById(@PathVariable Long id, Model model) {
        Optional<Abscence> absence = abscenceService.getAbscenceById(id);
        if (absence.isPresent()) {
            model.addAttribute("absence", absence.get());
            return "absenceDetail";
        }
        return "notFound";
    }

    @GetMapping("/addabsence")
    public String showAddAbsenceForm(Model model) {
        model.addAttribute("absence", new AbscenceDTO());
        return "addAbsence";
    }

    @PostMapping("/addabsence")
    public String createAbsence(
            @RequestParam Long studentId,
            @RequestParam Long courseId,
            @ModelAttribute AbscenceDTO absenceDTO,
            Model model) {

        AbscenceDTO createdAbsence = abscenceService.addAbscenceWithNames(studentId, courseId, absenceDTO);
        if (createdAbsence != null) {
            return "redirect:/teacher/absence/" + createdAbsence.getId();
        }
        model.addAttribute("error", "Unable to create absence");
        return "addAbsence";
    }

    @GetMapping("/updateabsence/{id}")
    public String showUpdateAbsenceForm(@PathVariable Long id, Model model) {
        Optional<Abscence> absence = abscenceService.getAbscenceById(id);
        if (absence.isPresent()) {
            model.addAttribute("absence", absence.get());
            return "updateAbsence";
        }
        return "notFound";
    }

    @PostMapping("/updateabsence/{id}")
    public String updateAbsence(@PathVariable Long id, @ModelAttribute Abscence absence, Model model) {
        Optional<Abscence> existingAbsence = abscenceService.getAbscenceById(id);
        if (existingAbsence.isPresent()) {
            abscenceService.saveAbscence(absence);
            return "redirect:/teacher/absence/" + id;
        }
        model.addAttribute("error", "Absence not found");
        return "updateAbsence";
    }

    @GetMapping("/deleteabsence/{id}")
    public String deleteAbsence(@PathVariable Long id) {
        abscenceService.deleteAbscence(id);
        return "redirect:/teacher/allabsences";
    }

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
        return "allCours";
    }

    @GetMapping("/addcours")
    public String showAddCoursForm(Model model) {
        model.addAttribute("cours", new Cours());
        return "addCours";
    }

    @PostMapping("/addcours")
    public String createCours(@ModelAttribute Cours cours, Model model) {
        Cours createdCours = coursService.createCours(cours);
        return "redirect:/teacher/cours/" + createdCours.getId();
    }

    @GetMapping("/updatecours/{id}")
    public String showUpdateCoursForm(@PathVariable Long id, Model model) {
        Optional<Cours> cours = coursService.getCoursById(id);
        if (cours.isPresent()) {
            model.addAttribute("cours", cours.get());
            return "updateCours";
        }
        return "notFound";
    }

    @PostMapping("/updatecours/{id}")
    public String updateCours(@PathVariable Long id, @ModelAttribute Cours cours, Model model) {
        Optional<Cours> existingCours = coursService.getCoursById(id);
        if (existingCours.isPresent()) {
            coursService.updateCours(id, cours);
            return "redirect:/teacher/cours/" + id;
        }
        model.addAttribute("error", "Cours not found");
        return "updateCours";
    }

    @GetMapping("/deletecours/{id}")
    public String deleteCours(@PathVariable Long id) {
        boolean deleted = coursService.deleteCours(id);
        if (deleted) {
            return "redirect:/teacher/allcours";
        }
        return "notFound";
    }
}
