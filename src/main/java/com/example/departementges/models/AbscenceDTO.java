package com.example.departementges.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="abscencesTdo")
public class AbscenceDTO {
    @Id
    @GeneratedValue
    private Long id;
    private String date;
    private String heure_debut;
    private String heure_fin;
    private String studentName;
    private String coursName;

    // Constructors, getters, and setters
}
