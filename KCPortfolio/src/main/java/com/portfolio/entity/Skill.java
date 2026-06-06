package com.portfolio.entity;
 
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
 
@Entity
@Table(name = "skills")
@Data
public class Skill {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @NotBlank
    @Column(nullable = false)
    private String name;
 
    @NotBlank
    @Column(nullable = false)
    private String category;  // e.g. "Frontend", "Backend", "DevOps", "Tools"
 
    @Min(0) @Max(100)
    @Column(nullable = false)
    private int proficiencyLevel;  // 0–100
 
    private String iconUrl;
    private int displayOrder = 0;
}
 