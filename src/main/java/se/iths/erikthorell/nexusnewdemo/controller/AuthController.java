package se.iths.erikthorell.nexusnewdemo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import se.iths.erikthorell.nexusnewdemo.dto.LoginRequest;
import se.iths.erikthorell.nexusnewdemo.dto.LoginResponse;
import se.iths.erikthorell.nexusnewdemo.security.JwtService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {


    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request
    ) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.username(),
                                request.password()
                        )
                );


        String username =
                authentication.getName();


        String role =
                authentication.getAuthorities()
                        .iterator()
                        .next()
                        .getAuthority()
                        .replace("ROLE_", "");


        String token =
                jwtService.generateToken(
                        username,
                        role
                );


        return ResponseEntity.ok(
                new LoginResponse(token)
        );
    }
}