package com.example.PlacementPulse.controller;

import com.example.PlacementPulse.dto.StudentUpdateDTO;
import com.example.PlacementPulse.model.Student;
import com.example.PlacementPulse.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
public class StudentController {
    //Object Creation
    @Autowired
    private StudentService studentServiceObj;


    //CREATE
    @PostMapping
    public ResponseEntity<Student> createStudent(
            @RequestBody Student student
    ) {
        Student createdStudent = studentServiceObj.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }

    //READ
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentServiceObj.getAllStudents();
        return ResponseEntity.status(HttpStatus.CREATED).body(students);
    }
    @GetMapping("/{rollNo}")
    public ResponseEntity<Student> getStudent(
            @PathVariable("rollNo") String rollNo
    ) {
        Student student = studentServiceObj.getStudentByRollNo(rollNo);
        return ResponseEntity.status(HttpStatus.OK).body(student);
    }

    //UPDATE
    @PutMapping("/{rollNo}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable("rollNo") String rollNo,
            @RequestBody StudentUpdateDTO studentUpdateDTO
            ) {
        Student student = studentServiceObj.updateStudent(rollNo, studentUpdateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(student);
    }
    @PutMapping("/{rollNo}/skills") //Add Skills
    public ResponseEntity<String> addSkill(
            @PathVariable String rollNo,
            @RequestBody String newSkill
        ) {
        studentServiceObj.addSkillToStudent(rollNo, newSkill);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Added skills to student " + rollNo + ": " + newSkill);
    }
    @PutMapping("/{rollNo}/interestedRoles") //Add Role
    public ResponseEntity<String> addInterestedRole(
            @PathVariable String rollNo,
            @RequestBody String role
        ) {
        studentServiceObj.addInterestedRoleToStudent(rollNo, role);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Added new interested role \"" + role + "\" to student with rollNo " + rollNo);
    }

    //DELETE
    @DeleteMapping("/{rollNo}")
    public ResponseEntity<String> deleteStudent(@PathVariable String rollNo) {
        studentServiceObj.deleteStudent(rollNo);
        return ResponseEntity.status(HttpStatus.OK).body("Student detail with rollNo: " + rollNo + " deleted.");
    }
    @DeleteMapping("/{rollNo}/skills") //Remove Skills
    public ResponseEntity<String> removeSkill(
            @PathVariable String rollNo,
            @RequestBody String skillToRemove
        ) {
        studentServiceObj.removeSkillFromStudent(rollNo, skillToRemove);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Deleted \"" + skillToRemove + "\" skill from student with rollNo " + rollNo);
    }
    @DeleteMapping("/{rollNo}/interestedRoles") //Delete Role
    public ResponseEntity<String> removeInterestedRole(
            @PathVariable String rollNo,
            @RequestBody String roleToRemove
        ) {
        studentServiceObj.removeInterestedRoleFromStudent(rollNo, roleToRemove);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Deleted \"" + roleToRemove + "\" role form student with rollNo " + rollNo);
    }

}