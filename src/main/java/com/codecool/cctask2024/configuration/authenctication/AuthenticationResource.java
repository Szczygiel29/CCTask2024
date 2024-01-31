package com.codecool.cctask2024.configuration.authenctication;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface AuthenticationResource {
    String REGISTER = "/register";
    String AUTHENTICATE = "/authenticate";

    @PostMapping(value = "/register")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Register user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Will return Token access to"),
            @ApiResponse(
                    responseCode = "401",
                    description = "Authentication failed"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error"),
    })
    ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request);


    @PostMapping(value = "/authenticate")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Authenticate user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Will return Token access to"),
            @ApiResponse(
                    responseCode = "401",
                    description = "Authentication failed"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error"),
    })
    ResponseEntity<AuthenticationResponse> authenticated(@RequestBody AuthenticationRequest request);
}
