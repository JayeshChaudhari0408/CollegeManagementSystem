package com.CollegeManagementSystem.repository;


import com.CollegeManagementSystem.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor,Long> {

}
