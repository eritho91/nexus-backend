package se.iths.erikthorell.nexusnewdemo.dto;

import java.util.List;

public record EmployeeDto(
        Long id,
        String firstName,
        String lastName,
        String username,
        String role,
        List<ShiftDto> shifts
) {
}