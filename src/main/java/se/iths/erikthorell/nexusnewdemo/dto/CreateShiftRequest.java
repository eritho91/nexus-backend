package se.iths.erikthorell.nexusnewdemo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

import java.time.LocalTime;

public record CreateShiftRequest(
        @NotBlank
        @Size(min = 1, max = 50)
        String location,
        @NotNull
        LocalDate date,
        @NotNull
        LocalTime time
) {
}