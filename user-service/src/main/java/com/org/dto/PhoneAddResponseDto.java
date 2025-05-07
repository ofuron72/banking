package com.org.dto;

import java.util.UUID;

public record PhoneAddResponseDto(
        UUID userId,
        String number
) {
}
