package com.org.dto;

import java.util.UUID;

public record PhoneAddRequestDto(
        UUID userId,
        String number
) {
}
