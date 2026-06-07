package com.portfolio.dto;

import com.portfolio.enums.ProjectStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ProjectRequestDTO {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    private String shortDescription;
    private String thumbnailUrl;
    private String liveUrl;
    private String githubUrl;

    private List<String> techStack;
    private List<String> imageUrls;

    private LocalDate startDate;
    private LocalDate endDate;

    private boolean featured = false;
    private int displayOrder = 0;

    private ProjectStatus status = ProjectStatus.COMPLETED;
}