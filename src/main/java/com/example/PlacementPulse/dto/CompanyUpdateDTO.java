package com.example.PlacementPulse.dto;

import com.example.PlacementPulse.model.Company;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyUpdateDTO {

    @NotBlank(message = "Company name is required!")
    private String companyName;

    @NotBlank(message = "Company website is required!")
    private String companyWebsite;

    @Positive(message = "Salary offering must be positive")
    private Double salaryOffering;

    @PositiveOrZero(message = "Intern period must be zero or more months")
    private Integer internPeriod;

    private List<@NotBlank String> studentEligibility;

    private Map<@NotBlank String, @Positive Integer> rolesWithVacancy;

    @NotNull(message = "Work location must be provided")
    private Company.WorkLocation workLocation;

    @NotNull(message = "Company type must be provided")
    private Company.CompanyType type;

    private Boolean hiringNow;
}