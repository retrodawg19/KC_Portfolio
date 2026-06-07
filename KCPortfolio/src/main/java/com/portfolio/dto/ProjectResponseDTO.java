package com.portfolio.dto;

import com.portfolio.entity.Project;
import com.portfolio.enums.ProjectStatus;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ProjectResponseDTO {

    private Long id;
    private String title;
    private String description;
    private String shortDescription;
    private String thumbnailUrl;
    private String liveUrl;
    private String githubUrl;
    private List<String> techStack;
    private List<String> imageUrls;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean featured;
    private int displayOrder;
    private ProjectStatus status;

   
}