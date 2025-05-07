package com.org.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.UUID;

public record PhoneAddRequestDto(

        @NotNull
        UUID userId,

        @NotNull
        @Pattern(regexp = "^\\d-\\d{3}-\\d{3}-\\d{2}-\\d{2}$",
                message = "number must match format x-xxx-xxx-xx-xx")
        String number
) {
}
