package org.orient.bookstorebackend.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.orient.bookstorebackend.model.request.LoginRequest;
import org.orient.bookstorebackend.model.request.RefreshTokenRequest;
import org.orient.bookstorebackend.model.request.UserRegisterRequest;
import org.orient.bookstorebackend.model.response.TokenResponse;
import org.orient.bookstorebackend.model.response.UserShortResponse;
import org.orient.bookstorebackend.service.AuthService;
import org.orient.bookstorebackend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {

    AuthService authService;
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserShortResponse> registerUser(@RequestBody UserRegisterRequest userRegisterRequest) {
        return ResponseEntity.status(201).body(userService.register(userRegisterRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest request) {
        TokenResponse tokenResponse = authService.login(request);
        return ResponseEntity.ok(tokenResponse);
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refresh(@RequestBody RefreshTokenRequest request) {
        TokenResponse tokenResponse = authService.refreshToken(request.getRefreshToken());
        return ResponseEntity.ok(tokenResponse);
    }

}