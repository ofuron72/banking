package com.org.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record AccountDto(
        UUID id,
        UUID userId,
        BigDecimal balance,
        BigDecimal initialDeposit,
        LocalDateTime updatedAt) {
}
