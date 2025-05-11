package com.org.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record EmailAddRequestDto(
        @NotNull
        UUID userId,
        @NotNull
        @Email
        String email
) {
}
