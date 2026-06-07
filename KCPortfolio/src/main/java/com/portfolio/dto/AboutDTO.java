package com.portfolio.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AboutDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Bio is required")
    private String bio;

    private String location;

    @Email(message = "Invalid email")
    private String email;

    private String phone;
    private String profileImageUrl;
    private String resumeUrl;
    private String githubUrl;
    private String linkedinUrl;
    private String twitterUrl;
}