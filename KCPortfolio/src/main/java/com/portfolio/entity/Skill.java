package com.portfolio.entity;
 
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Entity
@Table(name = "skills")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Skill {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @NotBlank
    @Column(nullable = false)
    private String name;
 
    @NotBlank
    @Column(nullable = false)
    private String category;   
    private String iconUrl;
    private int displayOrder = 0;
}
 