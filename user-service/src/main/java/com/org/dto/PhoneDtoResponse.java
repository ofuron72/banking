package com.org.dto;

import java.util.UUID;

public record PhoneDtoResponse(
        UUID userId,
        String number
) {
}
