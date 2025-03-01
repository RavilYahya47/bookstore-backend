package org.orient.bookstorebackend.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.orient.bookstorebackend.model.request.LoginRequest;
import org.orient.bookstorebackend.model.response.TokenResponse;
import org.orient.bookstorebackend.util.JwtTokenUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthService {

    UserService userService;
    JwtTokenUtil jwtTokenUtil;
//    AuthenticationManager authenticationManager;

    public TokenResponse login(LoginRequest loginRequest) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        loginRequest.getUsername(),
//                        loginRequest.getPassword()
//                )
//        );

        var user = userService.getUserByUsername(loginRequest.getUsername());

        var accessToken = jwtTokenUtil.generateAccessToken(user.getUsername());
        var refreshToken = jwtTokenUtil.generateRefreshToken(user.getUsername());

        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public TokenResponse refreshToken(String refreshToken) {
        if (!jwtTokenUtil.validateToken(refreshToken)) {
            throw new RuntimeException("Invalid refresh token");
        }

        var username = jwtTokenUtil.getUsernameFromToken(refreshToken);

        var user = userService.getUserByUsername(username);


        var newAccessToken = jwtTokenUtil.generateAccessToken(user.getUsername());
        var newRefreshToken = jwtTokenUtil.generateRefreshToken(user.getUsername());

        return TokenResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .build();
    }

}
