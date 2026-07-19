package se.iths.erikthorell.nexusnewdemo.dto;

public record LoginRequest(
        String username,
        String password
) {
}