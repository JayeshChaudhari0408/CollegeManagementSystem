package com.CollegeManagementSystem;

import com.CollegeManagementSystem.entity.Student;
import com.CollegeManagementSystem.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class StudentTest {

    @Autowired
    private StudentService studentService;

    @Test
    public void testCreateStudent() {
        Student student = Student.builder()
                .name("Trivesh")
                .build();
        studentService.createStudent(student);
    }

    @Test
    public void testGetStudentById() {
        studentService.getStudentById(1L);
    }


//    getAllStudents()
//    updateStudent(Long id, Student updated)
//    deleteStudent(Long id)
//    assignSubjectsToStudent(Long studentId, List<Long> subjectIds)
//    assignProfessorsToStudent(Long studentId, List<Long> professorIds)
//    getSubjectsByStudentId(Long studentId)
//    getProfessorsByStudentId(Long studentId)
}
