package com.CollegeManagementSystem.entity;

import jakarta.persistence.*;
import jakarta.servlet.annotation.HandlesTypes;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 50)
    private String title;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @ManyToMany(mappedBy = "subjects")
    private List<Student> students;
}
