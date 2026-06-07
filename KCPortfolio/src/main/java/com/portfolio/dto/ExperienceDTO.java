package com.portfolio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ExperienceDTO {

    @NotBlank(message = "Job title is required")
    private String jobTitle;

    @NotBlank(message = "Company is required")
    private String company;

    private String companyLogoUrl;
    private String location;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    private LocalDate endDate;           // null if currentJob = true

    private boolean currentJob = false;

    private String description;
    private List<String> responsibilities;
    private List<String> technologiesUsed;

    private int displayOrder = 0;
}