package com.example.PlacementPulse.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Student {
    // Field Overview (student Table)
    // student_roll_no
    // student_name
    // student_email
    // student_department
    // student_batch
    // resume_url
    // phone_number
    // placement_status
    // is_placed
    // is_placement_interested
    // registered_at
    // last_updated

    //Field Overview (student_skill Table)
    // student_student_roll_no
    // skills

    //Field Overview (student_interested_roles Table)
    // student_student_roll_no
    // interested_roles

    @Id
    @NotBlank(message = "Roll number is required!")
    private String studentRollNo;

    @NotBlank(message = "Student name is required!")
    private String studentName;

    @Email(message = "Email should be valid!")
    @NotBlank(message = "Student email is required!")
    private String studentEmail;

    @NotBlank(message = "Department is required!")
    private String studentDepartment;

    @Min(value = 2000, message = "Batch should be a valid year!")
    @Max(value = 2030, message = "Batch seems too far in the future!")
    private int studentBatch;

    @URL(message = "Resume URL must be a valid URL!")
    private String resumeUrl;

    @NotBlank(message = "Phone Number is required!")
    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be 10 digits!")
    private String phoneNumber;

    @ElementCollection
    @Size(min = 1, message = "At least one skill is required!")
    private List<@NotBlank(message = "Skill Cannot be blank.") String> skills = new ArrayList<>();

    @ElementCollection
    @Size(min = 1, message = "At least one interested role is required!")
    private List<@NotBlank(message = "Interested role cannot be blank.") String> interestedRoles = new ArrayList<>();

    public enum PlacementStatus { NotPlaced, Placed, InterviewScheduled, InProcess, Looking }
    @Enumerated(EnumType.STRING)
    private PlacementStatus placementStatus = PlacementStatus.NotPlaced;

    private boolean isPlaced = false;
    private boolean isPlacementInterested = true;

    @Column(updatable = false)
    private LocalDateTime registeredAt;

    private LocalDateTime lastUpdated;

    @PrePersist
    public void onCreate() {
        this.registeredAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.lastUpdated = LocalDateTime.now();
    }
}
