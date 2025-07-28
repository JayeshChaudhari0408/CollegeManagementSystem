package com.CollegeManagementSystem.service;

import com.CollegeManagementSystem.entity.Professor;
import com.CollegeManagementSystem.entity.Student;
import com.CollegeManagementSystem.entity.Subject;
import com.CollegeManagementSystem.repository.ProfessorRepository;
import com.CollegeManagementSystem.repository.StudentRepository;
import com.CollegeManagementSystem.repository.SubjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final ProfessorRepository professorRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    @Transactional
    public Subject createSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    public Subject getSubjectById(Long id) {
        return subjectRepository.findById(id).orElseThrow();
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @Transactional
    public Subject updateSubject(Long id, Subject updated) {
        Subject subject = subjectRepository.findById(id).orElseThrow();
        subject.setTitle(updated.getTitle());
        return subjectRepository.save(subject);
    }

    @Transactional
    public void deleteSubject(Long id) {
        subjectRepository.deleteById(id);
    }

    @Transactional
    public Subject assignProfessorToSubject(Long subjectId, Long professorId) {
        Subject subject = subjectRepository.findById(subjectId).orElseThrow();
        Professor professor = professorRepository.findById(professorId).orElseThrow();
        subject.setProfessor(professor);
        return subjectRepository.save(subject);
    }

    @Transactional
    public void assignStudentsToSubject(Long subjectId, List<Long> studentIds) {
        Subject subject = subjectRepository.findById(subjectId).orElseThrow();
        List<Student> students = studentRepository.findAllById(studentIds);
        for(Student student: students) {
            student.getSubjects().add(subject);
            subject.getStudents().add(student);
        }
        subjectRepository.save(subject);
        studentRepository.saveAll(students);
    }


    public List<Student> getStudentsBySubjectId(Long subjectId) {
        Subject subject =subjectRepository.findById(subjectId).orElseThrow();
        return subject.getStudents();
    }

    public Professor getProfessorBySubjectId(Long subjectId) {
        Subject subject =subjectRepository.findById(subjectId).orElseThrow();
        return subject.getProfessor();
    }

}
