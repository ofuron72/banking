package com.org.dto;

import java.util.UUID;

public record PhoneResponseDto(
        UUID userId,
        String number
) {
}
