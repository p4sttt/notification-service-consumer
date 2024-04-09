package net.d4y2k.consumer.kafka;

import net.d4y2k.consumer.utils.NotificationDeserializer;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfiguration {

	@Value("${spring.kafka.bootstrap-servers}")
	private String BOOTSTRAP_SERVERS;

	@Value("${kafka.topic}")
	private String TOPIC;

	@Value("${spring.kafka.consumer.group-id}")
	private String GROUP_ID;

	@Bean
	public ConsumerFactory<String, NotificationDto> consumerFactory() {
		Map<String, Object> props = new HashMap<>();

		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

		Deserializer<String> stringDeserializer = new ErrorHandlingDeserializer<>(new StringDeserializer());
		Deserializer<NotificationDto> notificationDeserializer = new ErrorHandlingDeserializer<>(new NotificationDeserializer());

		return new DefaultKafkaConsumerFactory<>(props, stringDeserializer, notificationDeserializer);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, NotificationDto> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, NotificationDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}

	@Bean
	NewTopic newTopic() {
		return TopicBuilder.name(TOPIC).partitions(1).replicas(1).build();
	}

}
