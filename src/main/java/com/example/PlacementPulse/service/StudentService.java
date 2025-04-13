package com.example.PlacementPulse.service;

import com.example.PlacementPulse.dto.StudentUpdateDTO;
import com.example.PlacementPulse.exception.ResourceNotFoundException;
import com.example.PlacementPulse.model.Student;
import com.example.PlacementPulse.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {
    //Object Creation
    private final StudentRepository studentRepoObj;

    //CREATE
    public Student createStudent(Student student) {
        student.setRegisteredAt(LocalDateTime.now());  // This will be set only during creation
        return studentRepoObj.save(student);
    }



    //READ
    public List<Student> getAllStudents() {
        return studentRepoObj.findAll(Sort.by("studentRollNo").ascending());
    }
    public Student getStudentByRollNo(String rollNo) {
        return studentRepoObj.findById(rollNo)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with rollNo: " + rollNo));
    }


    //UPDATE
    public Student updateStudent(String rollNo, StudentUpdateDTO dto) {
        Student student = studentRepoObj.findById(rollNo)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with rollNo: " + rollNo));

        if (dto.getStudentName() != null)
            student.setStudentName(dto.getStudentName());
        if (dto.getStudentEmail() != null)
            student.setStudentEmail(dto.getStudentEmail());
        if (dto.getStudentDepartment() != null)
            student.setStudentDepartment(dto.getStudentDepartment());
        if (dto.getStudentBatch() != null)
            student.setStudentBatch(dto.getStudentBatch());
        if (dto.getResumeUrl() != null)
            student.setResumeUrl(dto.getResumeUrl());
        if (dto.getPhoneNumber() != null)
            student.setPhoneNumber(dto.getPhoneNumber());
        if (dto.getSkills() != null)
            student.setSkills(dto.getSkills());
        if (dto.getInterestedRoles() != null)
            student.setInterestedRoles(dto.getInterestedRoles());
        if (dto.getIsPlaced() != null)
            student.setPlaced(dto.getIsPlaced());
        if (dto.getIsPlacementInterested() != null)
            student.setPlacementInterested(dto.getIsPlacementInterested());

        if (dto.getPlacementStatus() != null) {
            try {
                student.setPlacementStatus(Student.PlacementStatus.valueOf(dto.getPlacementStatus()));
            } catch (IllegalArgumentException e) {
                throw new ResourceNotFoundException("Invalid placement status: " + dto.getPlacementStatus());
            }
        }

        return studentRepoObj.save(student);
    }
    public void addSkillToStudent(String rollNo, String newSkill) {
        Student student = getStudentByRollNo(rollNo);

        List<String> skills = student.getSkills();
        if (!skills.contains(newSkill)) {
            skills.add(newSkill);
        }

        student.setSkills(skills);
        studentRepoObj.save(student);
    }
    public void addInterestedRoleToStudent(String rollNo, String role) {
        Student student = getStudentByRollNo(rollNo);

        List<String> interestedRoles = student.getInterestedRoles();
        if (!interestedRoles.contains(role)) {
            interestedRoles.add(role);
        }

        student.setInterestedRoles(interestedRoles);
        studentRepoObj.save(student);
    }

    // DELETE
    public void deleteStudent(String rollNo) {
        Student student = getStudentByRollNo(rollNo);
        studentRepoObj.delete(student);
    }
    public void removeSkillFromStudent(String rollNo, String skillToRemove) {
        Student student = getStudentByRollNo(rollNo);

        List<String> skills = student.getSkills();
        skills.remove(skillToRemove);

        student.setSkills(skills);
        studentRepoObj.save(student);
    }
    public void removeInterestedRoleFromStudent(String rollNo, String roleToRemove) {
        Student student = getStudentByRollNo(rollNo);

        List<String> interestedRoles = student.getInterestedRoles();
        interestedRoles.remove(roleToRemove);

        student.setInterestedRoles(interestedRoles);
        studentRepoObj.save(student);
    }
}
