package com.org.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record EmailDto(
        UUID userId,
        String email,
        LocalDateTime createdAt
) {
}
