package se.iths.erikthorell.nexusnewdemo.dto;

import java.time.LocalDateTime;

public record ShiftBookedMessage(
        String email,
        String name,
        String location,
        LocalDateTime shiftTime
) {
}
