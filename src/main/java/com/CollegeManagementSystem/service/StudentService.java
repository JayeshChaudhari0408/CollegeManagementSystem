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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final ProfessorRepository professorRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    @Transactional
    public Student createStudent(Student inputStudent){
        return studentRepository.save(inputStudent);
    }

    public Optional<Student> getStudentById(Long id){
        return studentRepository.findById(id);
    }

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    @Transactional
    public Student updateStudent(Long id, Student updated){
        Student findStudent = studentRepository.findById(id).orElseThrow();
        findStudent.setName(updated.getName());
        return studentRepository.save(findStudent);
    }

    @Transactional
    public void deleteStudentById(Long id){
        studentRepository.deleteById(id);
    }

    @Transactional
    public void assignSubjectsToStudent(Long studentId, List<Long> subjectIds) {
        Student student = studentRepository.findById(studentId).orElseThrow();
        List<Subject> subjects = subjectRepository.findAllById(subjectIds);
        for(Subject subject:  subjects) {
            student.getSubjects().add(subject);
            subject.getStudents().add(student);
        }
        subjectRepository.saveAll(subjects);
        studentRepository.save(student);
    }

    @Transactional
    public void assignProfessorsToStudent(Long studentId, List<Long> professorIds) {
        Student student = studentRepository.findById(studentId).orElseThrow();
        List<Professor> professors = professorRepository.findAllById(professorIds);

        for(Professor professor: professors) {
            student.getProfessors().add(professor);
            professor.getStudents().add(student);
        }
        professorRepository.saveAll(professors);
        studentRepository.save(student);
    }


    public List<Subject> getSubjectsByStudentId(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow();
        return student.getSubjects();
    }

    public List<Professor> getProfessorsByStudentId(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow();
        return student.getProfessors();
    }
}
