package com.org.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record AccountCreateEvent(
        @JsonProperty("userId") UUID userId,
        @JsonProperty("balance") BigDecimal balance,
        @JsonProperty("initialDeposit") BigDecimal initialDeposit,
        @JsonProperty("updatedAt") LocalDateTime updatedAt
) {
}
