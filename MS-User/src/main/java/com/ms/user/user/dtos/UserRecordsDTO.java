package com.ms.user.user.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRecordsDTO(@NotBlank String name,
                             @NotBlank @Email String email) {
}
