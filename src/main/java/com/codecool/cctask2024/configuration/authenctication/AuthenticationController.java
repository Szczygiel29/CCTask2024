package com.codecool.cctask2024.configuration.authenctication;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.codecool.cctask2024.configuration.ApiConstraints.AUTHENTICATION;


@RestController
@RequestMapping(AUTHENTICATION)
@RequiredArgsConstructor
public class AuthenticationController implements AuthenticationResource {

    private final AuthenticationService authenticationService;

    @PostMapping(REGISTER)
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping(AUTHENTICATE)
    public ResponseEntity<AuthenticationResponse> authenticated(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
