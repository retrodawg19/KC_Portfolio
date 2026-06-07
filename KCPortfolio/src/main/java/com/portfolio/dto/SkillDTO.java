package com.portfolio.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SkillDTO {

    @NotBlank(message = "Skill name is required")
    private String name;

    @NotBlank(message = "Category is required")
    private String category;

    @Min(value = 0, message = "Proficiency must be at least 0")
    @Max(value = 100, message = "Proficiency must be at most 100")
    private int proficiencyLevel;

    private String iconUrl;
    private int displayOrder = 0;
}