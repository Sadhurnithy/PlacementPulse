package com.example.PlacementPulse.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentUpdateDTO {

    private String studentName;

    @Email(message = "Email should be valid!")
    private String studentEmail;

    private String studentDepartment;

    private Integer studentBatch;

    @URL(message = "Resume URL must be valid!")
    private String resumeUrl;

    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be 10 digits!")
    private String phoneNumber;

    private List<String> skills;

    private List<String> interestedRoles;

    private Boolean isPlaced;

    private Boolean isPlacementInterested;

    private String placementStatus;
}
