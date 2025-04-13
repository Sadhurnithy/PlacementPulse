package com.example.PlacementPulse.service;

import com.example.PlacementPulse.dto.CompanyUpdateDTO;
import com.example.PlacementPulse.exception.ResourceNotFoundException;
import com.example.PlacementPulse.model.Company;
import com.example.PlacementPulse.model.Student;
import com.example.PlacementPulse.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {
    //Object Creation
    private final CompanyRepository companyRepoObj;

    //CREATE
    public Company createCompany(Company company) {
        return companyRepoObj.save(company);
    }


    //READ
    public List<Company> getAllCompanies() {
        return companyRepoObj.findAll();
    }
    public Company getCompanyById(Long companyId) {
        return companyRepoObj.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found."));
    }


    //UPDATE
    public Company updateCompany(Long companyId, CompanyUpdateDTO dto) {
        Company company = companyRepoObj.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with id: " + companyId));

        if (dto.getCompanyName() != null)
            company.setCompanyName(dto.getCompanyName());
        if (dto.getCompanyWebsite() != null)
            company.setCompanyWebsite(dto.getCompanyWebsite());
        if (dto.getSalaryOffering() != null)
            company.setSalaryOffering(dto.getSalaryOffering());
        if (dto.getInternPeriod() != null)
            company.setInternPeriod(dto.getInternPeriod());
        if (dto.getStudentEligibility() != null)
            company.setStudentEligibility(dto.getStudentEligibility());
        if (dto.getRolesWithVacancy() != null)
            company.setRolesWithVacancy(dto.getRolesWithVacancy());
        if (dto.getWorkLocation() != null)
            company.setWorkLocation(dto.getWorkLocation());
        if (dto.getType() != null)
            company.setType(dto.getType());
        if (dto.getHiringNow() != null)
            company.setHiringNow(dto.getHiringNow());

        return companyRepoObj.save(company);
    }


    //DELETE
    public void deleteCompany(Long companyId) {
        Company company = getCompanyById(companyId);
        companyRepoObj.delete(company);
    }
}
