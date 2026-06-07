package com.portfolio.entity;
 
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
 
@Entity
@Table(name = "projects")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @NotBlank
    @Column(nullable = false)
    private String title;
 
    @NotBlank
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;
 
    @Column(columnDefinition = "TEXT")
    private String shortDescription;
 
    private String thumbnailUrl;
    private String liveUrl;
    private String githubUrl;
 
    @ElementCollection
    @CollectionTable(name = "project_tech_stack", joinColumns = @JoinColumn(name = "project_id"))
    @Column(name = "technology")
    private List<String> techStack;
 
    @ElementCollection
    @CollectionTable(name = "project_images", joinColumns = @JoinColumn(name = "project_id"))
    @Column(name = "image_url")
    private List<String> imageUrls;
 
    private LocalDate startDate;
    private LocalDate endDate;
 
    private boolean featured = false;
    private int displayOrder = 0;
 
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProjectStatus status = ProjectStatus.COMPLETED;
 
    public enum ProjectStatus {
        IN_PROGRESS, COMPLETED, ARCHIVED
    }
}