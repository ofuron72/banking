package com.org.dto;

import java.time.LocalDate;
import java.util.UUID;

public record UserDto(
        UUID id,
        String fullName,
        LocalDate birthDate) {
}
