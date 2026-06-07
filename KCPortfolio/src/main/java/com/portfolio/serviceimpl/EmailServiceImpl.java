package com.portfolio.serviceimpl;

import com.portfolio.entity.Contact;
import com.portfolio.service.EmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Value("${portfolio.owner.email}")
    private String ownerEmail;

    @Value("${portfolio.owner.name}")
    private String ownerName;

    // Email 1: Notify you (the owner) about a new message
    public void sendOwnerNotification(Contact contact) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(ownerEmail);
            helper.setSubject("📬 New Contact: " + contact.getSubject());
            helper.setText(buildOwnerEmailHtml(contact), true);

            mailSender.send(message);
            log.info("Owner notification sent for contact from: {}", contact.getEmail());
        } catch (MessagingException e) {
            log.error("Failed to send owner notification email", e);
        }
    }

    // Email 2: Auto-reply to the person who filled the form
    public void sendAutoReply(Contact contact) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(contact.getEmail());
            helper.setFrom(ownerEmail, ownerName);
            helper.setSubject("Thanks for reaching out, " + contact.getName() + "!");
            helper.setText(buildAutoReplyHtml(contact), true);

            mailSender.send(message);
            log.info("Auto-reply sent to: {}", contact.getEmail());
        } catch (MessagingException e) {
            log.error("Failed to send auto-reply email", e);
        } catch (Exception e) {
            log.error("Unexpected error while sending auto-reply", e);
        }
    }

    private String buildOwnerEmailHtml(Contact contact) {
        return """
            <div style="font-family: Arial, sans-serif; max-width: 600px; margin: 0 auto; padding: 24px;">
              <h2 style="color: #1a1a2e;">New Contact Form Submission</h2>
              <table style="width:100%; border-collapse: collapse; margin-top: 16px;">
                <tr><td style="padding: 8px; font-weight: bold; color: #555;">Name</td>
                    <td style="padding: 8px;">%s</td></tr>
                <tr style="background:#f9f9f9"><td style="padding: 8px; font-weight: bold; color: #555;">Email</td>
                    <td style="padding: 8px;"><a href="mailto:%s">%s</a></td></tr>
                <tr><td style="padding: 8px; font-weight: bold; color: #555;">Subject</td>
                    <td style="padding: 8px;">%s</td></tr>
                <tr style="background:#f9f9f9"><td style="padding: 8px; font-weight: bold; color: #555; vertical-align: top;">Message</td>
                    <td style="padding: 8px; white-space: pre-wrap;">%s</td></tr>
              </table>
              <p style="margin-top: 24px; color: #888; font-size: 13px;">
                Received at: %s
              </p>
            </div>
            """.formatted(
                contact.getName(),
                contact.getEmail(), contact.getEmail(),
                contact.getSubject() != null ? contact.getSubject() : "(No subject)",
                contact.getMessage(),
                contact.getSubmittedAt().toString()
        );
    }

    private String buildAutoReplyHtml(Contact contact) {
        return """
            <div style="font-family: Arial, sans-serif; max-width: 600px; margin: 0 auto; padding: 24px;">
              <h2 style="color: #1a1a2e;">Hey %s, thanks for reaching out! 👋</h2>
              <p style="color: #333; line-height: 1.7;">
                I've received your message and will get back to you as soon as possible,
                usually within 1–2 business days.
              </p>
              <div style="background: #f4f6ff; border-left: 4px solid #4f46e5;
                          padding: 16px 20px; border-radius: 4px; margin: 24px 0;">
                <p style="margin: 0; color: #555; font-size: 14px; font-style: italic;">
                  "%s"
                </p>
              </div>
              <p style="color: #333; line-height: 1.7;">
                In the meantime, feel free to check out my work or connect with me on LinkedIn.
              </p>
              <p style="margin-top: 32px; color: #333;">
                Best regards,<br/>
                <strong>%s</strong>
              </p>
              <hr style="border: none; border-top: 1px solid #eee; margin: 24px 0;"/>
              <p style="color: #aaa; font-size: 12px;">
                This is an automated reply. Please do not reply to this email directly.
              </p>
            </div>
            """.formatted(
                contact.getName(),
                contact.getMessage().length() > 200
                    ? contact.getMessage().substring(0, 200) + "..."
                    : contact.getMessage(),
                ownerName
        );
    }
}