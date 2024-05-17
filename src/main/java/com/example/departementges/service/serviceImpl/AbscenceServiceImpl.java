package com.example.departementges.service.serviceImpl;

import com.example.departementges.models.Abscence;
import com.example.departementges.models.AbscenceDTO;
import com.example.departementges.models.Cours;
import com.example.departementges.models.User;
import com.example.departementges.repository.AbscenceRepository;
import com.example.departementges.service.AbscenceService;
import com.example.departementges.service.CoursService;
import com.example.departementges.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AbscenceServiceImpl implements AbscenceService {

    private final AbscenceRepository abscenceRepository;
    private final UserService userService;
    private final CoursService coursService;

    @Autowired
    public AbscenceServiceImpl(AbscenceRepository abscenceRepository, UserService userService, CoursService coursService) {
        this.abscenceRepository = abscenceRepository;
        this.userService = userService;
        this.coursService = coursService;
    }
    @Override
    public AbscenceDTO addAbscenceWithNames(Long studentId, Long courseId, AbscenceDTO abs) {
        User student = userService.getUserById(studentId);
        Cours cours = coursService.getCoursById(courseId).orElse(null);

        if (student == null || cours == null) {
            return null; // Or handle the error appropriately
        }

        Abscence absence = new Abscence();
        absence.setDate(abs.getDate());
        absence.setHeure_debut(abs.getHeure_debut());
        absence.setHeure_fin(abs.getHeure_fin());
        absence.setStudent(student);
        absence.setCours(cours);

        Abscence savedAbscence = abscenceRepository.save(absence);

        AbscenceDTO savedAbscenceDTO = new AbscenceDTO();
        savedAbscenceDTO.setId(savedAbscence.getId());
        savedAbscenceDTO.setDate(savedAbscence.getDate());
        savedAbscenceDTO.setHeure_debut(savedAbscence.getHeure_debut());
        savedAbscenceDTO.setHeure_fin(savedAbscence.getHeure_fin());
        savedAbscenceDTO.setStudentName(student.getName());
        savedAbscenceDTO.setCoursName(cours.getName());

        return savedAbscenceDTO;
    }

    @Override
    public List<Abscence> getAllAbscences() {
        return abscenceRepository.findAll();
    }

    @Override
    public Optional<Abscence> getAbscenceById(Long id) {
        return abscenceRepository.findById(id);
    }

    @Override
    public Abscence saveAbscence(Abscence abscence) {
        return abscenceRepository.save(abscence);
    }

    @Override
    public void deleteAbscence(Long id) {
        abscenceRepository.deleteById(id);
    }
}
