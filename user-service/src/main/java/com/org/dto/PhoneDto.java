package com.org.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record PhoneDto(
        UUID userId,
        String number,
        LocalDateTime createdAt
) {
}
