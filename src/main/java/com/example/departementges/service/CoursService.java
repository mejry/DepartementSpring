package com.example.departementges.service;

import com.example.departementges.models.Cours;

import java.util.List;
import java.util.Optional;

public interface CoursService {
    List<Cours> getAllCours();
    Optional<Cours> getCoursById(Long id);
    Cours createCours(Cours cours);
    Cours updateCours(Long id, Cours coursDetails);
    boolean deleteCours(Long id);

}
