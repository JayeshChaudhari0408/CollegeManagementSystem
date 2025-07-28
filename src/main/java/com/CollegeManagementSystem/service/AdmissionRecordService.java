package com.CollegeManagementSystem.service;

import com.CollegeManagementSystem.entity.AdmissionRecord;
import com.CollegeManagementSystem.entity.Student;
import com.CollegeManagementSystem.repository.AdmissionRecordRepository;
import com.CollegeManagementSystem.repository.ProfessorRepository;
import com.CollegeManagementSystem.repository.StudentRepository;
import com.CollegeManagementSystem.repository.SubjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdmissionRecordService {
    private final StudentRepository studentRepository;
    private final AdmissionRecordRepository admissionRecordRepository;

    @Transactional
    public void createAdmissionRecord(Long studentId, AdmissionRecord record) {
        Student student =  studentRepository.findById(studentId).orElseThrow();
        record.setStudent(student);
        AdmissionRecord admissionRecord = admissionRecordRepository.save(record);
        student.setAdmissionRecord(admissionRecord);
        studentRepository.save(student);
    }

    public AdmissionRecord getAdmissionById(Long id) {
        return admissionRecordRepository.findById(id).orElseThrow();
    }

    public List<AdmissionRecord> getAllAdmissions() {
        List<AdmissionRecord> admissionRecord = admissionRecordRepository.findAll();
        return admissionRecord;
    }

    @Transactional
    public AdmissionRecord updateAdmission(Long id, AdmissionRecord record) {
        AdmissionRecord admissionRecord = admissionRecordRepository.findById(id).orElseThrow();
        admissionRecord.setFees(record.getFees());
        admissionRecord.setStudent(record.getStudent());
        return admissionRecordRepository.save(admissionRecord);
    }

    @Transactional
    public void deleteAdmission(Long id) {
        admissionRecordRepository.deleteById(id);
    }

    public AdmissionRecord getAdmissionByStudentId(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow();
        return student.getAdmissionRecord();
    }

    public Long getTotalFeesCollected() {
        Long totalFees=0L;
        List<AdmissionRecord> admissionRecords =  admissionRecordRepository.findAll();
        for(AdmissionRecord admissionRecord:admissionRecords) {
            totalFees = totalFees + admissionRecord.getFees();
        }
        return totalFees;
    }

}
