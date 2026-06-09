package com.portfolio.controller;

import com.portfolio.dto.ApiResponseDTO;
import com.portfolio.dto.ContactRequestDTO;
import com.portfolio.dto.ContactResponseDTO;
import com.portfolio.entity.Contact.ContactStatus;
import com.portfolio.service.ContactService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class ContactController {

    private final ContactService contactService;

    @PostMapping("/contact")
    public ResponseEntity<ApiResponseDTO<Void>> submitContact(@Valid @RequestBody ContactRequestDTO dto) {
        contactService.submitContact(dto);
        ApiResponseDTO<Void> response = ApiResponseDTO.success("Your message has been sent! I'll get back to you soon.", null);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/admin/contacts")
    public ResponseEntity<ApiResponseDTO<List<ContactResponseDTO>>> getAllContacts() {
        ApiResponseDTO<List<ContactResponseDTO>> response = ApiResponseDTO.success(contactService.getAllContacts());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/admin/contacts/{id}/status")
    public ResponseEntity<ApiResponseDTO<ContactResponseDTO>> updateStatus(
            @PathVariable Long id, @RequestParam ContactStatus status) {
        ApiResponseDTO<ContactResponseDTO> response = ApiResponseDTO.success("Status updated", contactService.updateStatus(id, status));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/admin/contacts/{id}")
    public ResponseEntity<ApiResponseDTO<Void>> deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
        ApiResponseDTO<Void> response = ApiResponseDTO.success("Contact deleted successfully", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}