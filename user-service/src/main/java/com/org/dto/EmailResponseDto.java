package com.org.dto;

import java.util.UUID;

public record EmailResponseDto(
        UUID userId,
        String email
) {
}
