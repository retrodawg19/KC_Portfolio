package com.portfolio.entity;
 
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
 
@Entity
@Table(name = "experience")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Experience {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @NotBlank
    @Column(nullable = false)
    private String jobTitle;
 
    @NotBlank
    @Column(nullable = false)
    private String company;
 
    private String companyLogoUrl;
    private String location;
 
    @NotBlank
    @Column(nullable = false)
    private LocalDate startDate;
 
    private LocalDate endDate;  // null = current job
 
    private boolean currentJob = false;
 
    @Column(columnDefinition = "TEXT")
    private String description;
 
    @ElementCollection
    @CollectionTable(name = "experience_responsibilities", joinColumns = @JoinColumn(name = "experience_id"))
    @Column(name = "responsibility", columnDefinition = "TEXT")
    private List<String> responsibilities;
 
    @ElementCollection
    @CollectionTable(name = "experience_tech_used", joinColumns = @JoinColumn(name = "experience_id"))
    @Column(name = "technology")
    private List<String> technologiesUsed;
 
    private int displayOrder = 0;

	
}