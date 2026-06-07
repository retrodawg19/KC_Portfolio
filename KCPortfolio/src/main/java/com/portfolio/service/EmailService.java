package com.portfolio.service;

import org.springframework.stereotype.Service;

import com.portfolio.entity.Contact;

@Service
public interface EmailService {

	void sendOwnerNotification(Contact saved);

	void sendAutoReply(Contact saved);

}
