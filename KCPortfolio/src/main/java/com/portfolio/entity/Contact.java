package com.portfolio.entity;
 
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
 
import java.time.LocalDateTime;
 
@Entity
@Table(name = "contacts")
@Data
public class Contact {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @NotBlank
    @Column(nullable = false)
    private String name;
 
    @Email
    @NotBlank
    @Column(nullable = false)
    private String email;
 
    private String subject;
 
    @NotBlank
    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;
 
    @Column(nullable = false, updatable = false)
    private LocalDateTime submittedAt = LocalDateTime.now();
 
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ContactStatus status = ContactStatus.UNREAD;
 
    public enum ContactStatus {
        UNREAD, READ, REPLIED, ARCHIVED
    }
}