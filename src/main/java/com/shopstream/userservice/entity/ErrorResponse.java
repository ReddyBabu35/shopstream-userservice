package com.shopstream.userservice.entity;

import java.time.LocalDateTime;

public record ErrorResponse(
		int status,
		String message,
		String path,
		LocalDateTime time) {

}
