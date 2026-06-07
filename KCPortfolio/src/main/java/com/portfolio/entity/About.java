package com.portfolio.entity;
 
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Entity
@Table(name = "about")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class About {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @NotBlank
    @Column(nullable = false)
    private String name;
 
    @NotBlank
    @Column(nullable = false)
    private String title;
 
    @NotBlank
    @Column(nullable = false, columnDefinition = "TEXT")
    private String bio;
 
    private String location;
    private String email;
    private String phone;
    private String profileImageUrl;
    private String resumeUrl;
    private String githubUrl;
    private String linkedinUrl;
    private String twitterUrl;
}