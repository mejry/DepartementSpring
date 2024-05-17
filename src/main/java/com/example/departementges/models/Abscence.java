package com.example.departementges.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="abscences")
public class Abscence {
    @Id
    @GeneratedValue
    private Long id;

    private String date;
    private String heure_debut;
    private String heure_fin;
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    @ManyToOne
    @JoinColumn(name = "cours_id", nullable = false)
    private Cours cours;

    @Override
    public String toString() {
        return "Abscence{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", heure_debut='" + heure_debut + '\'' +
                ", heure_fin='" + heure_fin + '\'' +
                ", studentName='" + student.getName() + '\'' +
                ", coursName='" + cours.getName() + '\'' +
                '}';
    }
}
