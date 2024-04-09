package net.d4y2k.consumer.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.d4y2k.consumer.services.EmailService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class NotificationListener {

	private final EmailService emailService;

	@KafkaListener(
			topics = "${kafka.topic}",
			groupId = "${spring.kafka.consumer.group-id}",
			containerFactory = "kafkaListenerContainerFactory"
	)
	public void listen(NotificationDto notificationDto) {
		log.debug("Received notification: {}", notificationDto);

		emailService.sendEmail(
				notificationDto.getEmail(),
				notificationDto.getTitle(),
				notificationDto.getMessage()
		);
	}

}
