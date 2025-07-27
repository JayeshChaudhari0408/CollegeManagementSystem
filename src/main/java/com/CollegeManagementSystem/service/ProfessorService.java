package com.CollegeManagementSystem.service;

import com.CollegeManagementSystem.entity.Professor;
import com.CollegeManagementSystem.entity.Student;
import com.CollegeManagementSystem.entity.Subject;
import com.CollegeManagementSystem.repository.ProfessorRepository;
import com.CollegeManagementSystem.repository.StudentRepository;
import com.CollegeManagementSystem.repository.SubjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;


    @Transactional
    public Professor createProfessor(Professor professor) {
        return professorRepository.save(professor);
    }

    @Transactional
    public Professor getProfessorById(Long professorId) {
        Professor professor = professorRepository.findById(professorId).orElseThrow();
        return professor;
    }

    @Transactional
    public List<Professor> getAllProfessors() {
        List<Professor> professor = professorRepository.findAll();
        return professor;
    }

    @Transactional
    public Professor updateProfessor(Long professorId, Professor professor) {
        Professor professor1= professorRepository.findById(professorId).orElseThrow();
        professor1.setTitle(professor.getTitle());
        return professorRepository.save(professor1);
    }

    @Transactional
    public void deleteProfessor(Long professorId) {
        professorRepository.deleteById(professorId);
    }

    @Transactional
    public void assignProfessorToStudent(Long professorId, List<Long> studentId) {
        Professor professor = professorRepository.findById(professorId).orElseThrow();
        List<Student> students = studentRepository.findAllById(studentId);
        for(Student student : students) {
            student.getProfessors().add(professor);
            professor.getStudents().add(student);
        }
//        professor.getStudents().addAll(students);
        professorRepository.save(professor);
        studentRepository.saveAll(students);
    }

    @Transactional
    public void assignProfessorsToSubjects(Long professor,List<Long> subject) {
        Professor professors = professorRepository.findById(professor).orElseThrow();
        List<Subject> subjects = subjectRepository.findAllById(subject);

        for(Subject subject1 : subjects) {
            subject1.setProfessor(professors);
        }
        subjectRepository.saveAll(subjects);
    }

    @Transactional
    public List<Student> getStudentsByProfessorId(Long professorId) {
        Professor professor = professorRepository.findById(professorId).orElseThrow();
        return professor.getStudents();
    }

    @Transactional
    public List<Subject> getSubjectsByProfessorId(Long professorId) {
        Professor professor = professorRepository.findById(professorId).orElseThrow();
        return professor.getSubjects();
    }


}
