package com.org.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record RegisterUserResponseDto(
        UUID id,
        String fullName,
        LocalDate birthDate,
        String email,
        BigDecimal initialDeposit) {

}
