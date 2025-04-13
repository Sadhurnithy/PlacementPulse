package com.example.PlacementPulse.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Company {
    // Field Overview (company Table)
    // id
    // company_name
    // company_website
    // salary_offering
    // intern_period
    // work_location
    // type
    // is_hiring_now

    // Field Overview (company_student_eligibility Table)
    // company_id
    // student_eligibility

    // Field Overview (company_roles_with_vacancy Table)
    // company_id
    // roles_with_vacancy_key
    // roles_with_vacancy


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Company name is required!")
    private String companyName;

    @NotBlank(message = "Company website is required")
    private String companyWebsite;

    @Positive(message = "Salary offering must be positive")
    private double salaryOffering;

    @PositiveOrZero(message = "Intern period must be zero or more months")
    private int internPeriod;

    @ElementCollection
    private List<@NotBlank String> studentEligibility;

    @ElementCollection
    private Map<@NotBlank String, @Positive Integer> rolesWithVacancy;

    public enum WorkLocation { Remote, OnSite, WorkFromHome }
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Work location must be provided")
    private WorkLocation workLocation;

    public enum CompanyType { Service, Product, StartUp, Consultancy }

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Company type is required")
    private CompanyType type;

    private boolean isHiringNow = true;
}
