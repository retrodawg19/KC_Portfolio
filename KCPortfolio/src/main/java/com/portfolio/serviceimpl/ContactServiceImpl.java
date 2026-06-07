package com.portfolio.serviceimpl;

import com.portfolio.dto.ContactRequestDTO;
import com.portfolio.entity.Contact;
import com.portfolio.repository.ContactRepository;
import com.portfolio.service.ContactService;
import com.portfolio.service.EmailService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ContactServiceImpl implements ContactService{

    private  ContactRepository contactRepository;
    private  EmailService emailService;

    public Contact submitContact(ContactRequestDTO dto) {
        // 1. Save to database
        Contact contact = new Contact();
        contact.setName(dto.getName());
        contact.setEmail(dto.getEmail());
        contact.setSubject(dto.getSubject());
        contact.setMessage(dto.getMessage());
        Contact saved = contactRepository.save(contact);

        // 2. Notify you (the owner)
        emailService.sendOwnerNotification(saved);

        // 3. Auto-reply to the sender — "Thank you for reaching out"
        emailService.sendAutoReply(saved);

        log.info("Contact form submitted by: {}", saved.getEmail());
        return saved;
    }

    // Admin: view all messages
    public List<Contact> getAllContacts() {
        return contactRepository.findAllByOrderBySubmittedAtDesc();
    }

    // Admin: mark a message as read/replied/archived
    public Contact updateStatus(Long id, Contact.ContactStatus status) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact not found with id: " + id));
        contact.setStatus(status);
        return contactRepository.save(contact);
    }

    public void deleteContact(Long id) {
        if (!contactRepository.existsById(id)) {
            throw new RuntimeException("Contact not found with id: " + id);
        }
        contactRepository.deleteById(id);
    }
}