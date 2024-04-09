package net.d4y2k.consumer.kafka;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class NotificationDto {

	private String email;

	private String title;

	private String message;

	@JsonCreator
	public NotificationDto(
			@JsonProperty("email") String email,
			@JsonProperty("title") String title,
			@JsonProperty("message") String message
	) {
		this.email = email;
		this.title = title;
		this.message = message;
	}

}
