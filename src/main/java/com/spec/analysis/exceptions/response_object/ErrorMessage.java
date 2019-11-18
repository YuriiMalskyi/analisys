package com.spec.analysis.exceptions.response_object;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorMessage {

	private String message;
	
	@JsonFormat(pattern = "yyyy-mm-dd hh:mm:ss")
	private LocalDateTime time;
	
	public ErrorMessage(String message) {
		this.message = message;
		this.time = LocalDateTime.now();
	}
	
}
