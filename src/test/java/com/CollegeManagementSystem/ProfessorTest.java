package com.CollegeManagementSystem;

import com.CollegeManagementSystem.entity.Professor;
import com.CollegeManagementSystem.entity.Student;
import com.CollegeManagementSystem.repository.ProfessorRepository;
import com.CollegeManagementSystem.service.ProfessorService;
import jakarta.transaction.Transactional;
import lombok.ToString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ProfessorTest {

    @Autowired
    private ProfessorService professorService;

    @Test
    public void testProfessorAssignToStudent() {
        List<Long> studentId = new ArrayList<>();
        studentId.add(1L);
        studentId.add(2L);
        professorService.assignProfessorToStudent(1L,studentId);
    }

    @Test
    public void testProfessorsAssignToSubjects() {
        List<Long> subjectId = new ArrayList<>();
        subjectId.add(1L);
        subjectId.add(3L);
        professorService.assignProfessorsToSubjects(1L,subjectId);
    }

    @Test
    public void testCreateProfessor() {
        Professor professor = Professor.builder()
                .title("Jayesh Chaudhari")
                .build();
        professorService.createProfessor(professor);
    }
    @Test
    public void testFindProfessorById() {
        professorService.getProfessorById(3L);
    }

    @Transactional
    @Test
    public void testFindAllProfessorById() {
        System.out.println(professorService.getAllProfessors());
    }

    @Test
    public void testUpdateProfessor() {
        Professor professor = Professor.builder()
                .title("Jayesh Chaudhari")
                .build();
        professorService.updateProfessor(1L,professor);
    }

    @Test
    public void testDeleteProfessor() {
        professorService.deleteProfessor(1L);
    }

    @Test
    @Transactional
    public void testGetStudentsByProfessorId() {
        System.out.println(professorService.getStudentsByProfessorId(2L));
    }

    @Test
    @Transactional
    public void testGetSubjectsByProfessorId() {
        System.out.println(professorService.getSubjectsByProfessorId(2L));
    }

}
