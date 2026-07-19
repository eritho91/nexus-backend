package se.iths.erikthorell.nexusnewdemo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public record ShiftDto(
        Long id,
        String location,

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        LocalDateTime shiftTime,

        List<EmployeeSummaryDto> employees
) {
}