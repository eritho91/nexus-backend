package se.iths.erikthorell.nexusnewdemo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.time.LocalTime;
import java.time.OffsetDateTime;

public record CreateShiftRequest(
        String location,
        LocalDate date,
        LocalTime time
) {
}