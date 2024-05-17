package com.example.departementges.service;

import com.example.departementges.models.Abscence;
import com.example.departementges.models.AbscenceDTO;

import java.util.List;
import java.util.Optional;

public interface AbscenceService {
    List<Abscence> getAllAbscences();
    Optional<Abscence> getAbscenceById(Long id);
    Abscence saveAbscence(Abscence abscence);
    void deleteAbscence(Long id);
    AbscenceDTO addAbscenceWithNames(Long studentId, Long courseId, AbscenceDTO abs);
}