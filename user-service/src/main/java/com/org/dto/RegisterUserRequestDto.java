package com.org.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RegisterUserRequestDto(
        @NotNull
        String fullName,
        @NotNull
        LocalDate birthDate,
        @NotNull
        @Pattern(regexp = "^\\d-\\d{3}-\\d{3}-\\d{2}-\\d{2}$",
                message = "number must match format x-xxx-xxx-xx-xx")
        String number,

        @NotNull
        @Email(message = "Email address has invalid format")
        String email,
        BigDecimal initialDeposit
) {
}
