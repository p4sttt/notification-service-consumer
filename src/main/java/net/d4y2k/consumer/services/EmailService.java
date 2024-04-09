package net.d4y2k.consumer.services;

public interface EmailService {
	void sendEmail(String to, String subject, String message);
}
