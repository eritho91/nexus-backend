package se.iths.erikthorell.nexusnewdemo.dto;

public record CreateEmployeeRequest(
        String firstName,
        String lastName,
        String password
) {
}
