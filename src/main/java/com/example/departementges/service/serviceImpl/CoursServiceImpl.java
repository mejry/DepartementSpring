package com.example.departementges.service.serviceImpl;

import com.example.departementges.models.Cours;
import com.example.departementges.repository.CoursRepository;
import com.example.departementges.service.CoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoursServiceImpl implements CoursService {
    private final CoursRepository coursRepository;

    @Autowired
    public CoursServiceImpl(CoursRepository coursRepository) {
        this.coursRepository = coursRepository;
    }

    @Override
    public List<Cours> getAllCours() {
        return coursRepository.findAll();
    }

    @Override
    public Optional<Cours> getCoursById(Long id) {
        return coursRepository.findById(id);
    }

    @Override
    public Cours createCours(Cours cours) {
        return coursRepository.save(cours);
    }

    @Override
    public Cours updateCours(Long id, Cours coursDetails) {
        Optional<Cours> optionalCours = coursRepository.findById(id);
        if (optionalCours.isPresent()) {
            Cours existingCours = optionalCours.get();
            existingCours.setName(coursDetails.getName());
            existingCours.setDuree(coursDetails.getDuree());
            return coursRepository.save(existingCours);
        } else {
            throw new RuntimeException("Cours not found with id: " + id);
        }
    }

    @Override
    public boolean deleteCours(Long id) {
        coursRepository.deleteById(id);
        return false;
    }
}
