package net.d4y2k.consumer.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.d4y2k.consumer.services.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {

	private final JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String SEND_FROM;

	@Override
	public void sendEmail(String to, String subject, String message) {
		log.debug("Sending email to: {}", to);

		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom(SEND_FROM);
		mailMessage.setTo(to);
		mailMessage.setSubject(subject);
		mailMessage.setText(message);

		try {
			javaMailSender.send(mailMessage);
			log.debug("Email sent to: {}", to);
		} catch (MailException mailException) {
			log.error("Failed to send email to: {}", to, mailException);
		}
	}
}
