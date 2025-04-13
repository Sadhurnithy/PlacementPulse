package com.example.PlacementPulse.controller;

import com.example.PlacementPulse.dto.CompanyUpdateDTO;
import com.example.PlacementPulse.model.Company;
import com.example.PlacementPulse.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyController {
    //Object Creation
    private final CompanyService companyServiceObj;


    //CREATE
    @PostMapping
    public ResponseEntity<Company> createCompany(
            @RequestBody
            @Valid
            Company company
            ) {
        Company createdCompany = companyServiceObj.createCompany(company);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCompany);
    }

    //READ
    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companies = companyServiceObj.getAllCompanies();
        return ResponseEntity.status(HttpStatus.FOUND).body(companies);
    }
    @GetMapping("/{companyId}")
    public ResponseEntity<Company> getCompany(
            @PathVariable("companyId") Long companyId
    ) {
        Company companies = companyServiceObj.getCompanyById(companyId);
        return ResponseEntity.status(HttpStatus.OK).body(companies);
    }

    //UPDATE
    @PutMapping("/{companyId}")
    public ResponseEntity<Company> updateCompany(
            @PathVariable("companyId") Long companyId,
            @RequestBody @Valid CompanyUpdateDTO companyDataDto
    ) {
        Company updatedCompany = companyServiceObj.updateCompany(companyId, companyDataDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedCompany);
    }

    //DELETE
    @DeleteMapping("/{companyId}")
    public ResponseEntity<String> deleteCompany(
            @PathVariable("companyId") Long companyId
    ) {
        companyServiceObj.deleteCompany(companyId);
        return ResponseEntity.status(HttpStatus.OK).body("Company with Id " + companyId + " deleted.");
    }
}
