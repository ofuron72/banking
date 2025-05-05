package com.org.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record RegisterUserRequestDto(
        String fullName,
        LocalDate birthDate,
        List<String> phones,
        List<String> emails,
        BigDecimal initialDeposit

) {
}
