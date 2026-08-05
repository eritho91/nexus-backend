package se.iths.erikthorell.nexusnewdemo.dto;

import jakarta.validation.constraints.*;

public record CreateEmployeeRequest(
        @NotBlank
        @Size(min = 3, max = 50)
        String firstName,
        @NotBlank
        @Size(min = 3, max = 50)
        String lastName,
        @NotBlank
        @Size(min = 8, max = 100)
        String password
) {
}
