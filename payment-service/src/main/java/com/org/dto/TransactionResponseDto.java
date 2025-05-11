package com.org.dto;

import java.math.BigDecimal;

public record TransactionResponseDto(String responseMessage,
                                     BigDecimal amount) {
}
