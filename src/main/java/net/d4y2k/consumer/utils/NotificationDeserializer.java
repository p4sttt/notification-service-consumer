package net.d4y2k.consumer.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.d4y2k.consumer.kafka.NotificationDto;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
public class NotificationDeserializer implements Deserializer<NotificationDto> {
	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
	}

	@Override
	public NotificationDto deserialize(String topic, byte[] data) {
		try {
			if (data == null){
				return null;
			}

			return objectMapper.readValue(new String(data, StandardCharsets.UTF_8), NotificationDto.class);
		} catch (Exception e) {
			throw new SerializationException("Error when deserializing byte[] to MessageDto");
		}
	}

	@Override
	public void close() {
	}
}

