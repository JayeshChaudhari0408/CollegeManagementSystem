package com.CollegeManagementSystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @OneToMany(mappedBy = "professor",cascade = CascadeType.ALL)
    private List<Subject> subjects;

    @ManyToMany(mappedBy = "professors", cascade = CascadeType.ALL)
    private List<Student> students;

}
