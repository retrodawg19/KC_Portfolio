package com.portfolio.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.portfolio.dto.ContactRequestDTO;
import com.portfolio.dto.ContactResponseDTO;
import com.portfolio.entity.Contact.ContactStatus;

@Service
public interface ContactService {

	void submitContact(ContactRequestDTO dto);

	List<ContactResponseDTO> getAllContacts();

	ContactResponseDTO updateStatus(Long id, ContactStatus status);

	void deleteContact(Long id);

}
