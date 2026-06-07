package com.portfolio.dto;

import com.portfolio.entity.Contact;
import com.portfolio.enums.ContactStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ContactResponseDTO {

    private Long id;
    private String name;
    private String email;
    private String subject;
    private String message;
    private LocalDateTime submittedAt;
    private ContactStatus status;

}