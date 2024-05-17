package com.example.departementges.repository;

import com.example.departementges.models.Abscence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbscenceRepository  extends JpaRepository<Abscence, Long> {
}
