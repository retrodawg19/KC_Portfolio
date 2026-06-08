package com.portfolio.serviceimpl;

import com.portfolio.dto.ContactRequestDTO;
import com.portfolio.dto.ContactResponseDTO;
import com.portfolio.entity.Contact;
import com.portfolio.mapper.ContactMapper;
import com.portfolio.repository.ContactRepository;
import com.portfolio.service.ContactService;
import com.portfolio.service.EmailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final EmailService emailService;
    private final ContactMapper contactMapper;

    @Override
    public void submitContact(ContactRequestDTO dto) {
        Contact contact = contactMapper.toEntity(dto);  // maps name, email, subject, message
        Contact saved = contactRepository.save(contact);
        emailService.sendOwnerNotification(saved);
        emailService.sendAutoReply(saved);
        log.info("Contact form submitted by: {}", saved.getEmail());
    }

    @Override
    public List<ContactResponseDTO> getAllContacts() {
        return contactRepository.findAllByOrderBySubmittedAtDesc()
                .stream()
                .map(contactMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ContactResponseDTO updateStatus(Long id, Contact.ContactStatus status) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact not found with id: " + id));
        contact.setStatus(status);
        return contactMapper.toResponseDTO(contactRepository.save(contact));
    }

    @Override
    public void deleteContact(Long id) {
        if (!contactRepository.existsById(id)) {
            throw new RuntimeException("Contact not found with id: " + id);
        }
        contactRepository.deleteById(id);
        log.info("Contact deleted with id: {}", id);
    }
}