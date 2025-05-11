package com.org.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record TransactionCompletedEvent(
        UUID fromAccountId,
        UUID toAccountId,
        BigDecimal amount
) {
}
