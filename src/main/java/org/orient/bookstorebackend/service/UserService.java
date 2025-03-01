package org.orient.bookstorebackend.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.orient.bookstorebackend.exception.EntityNotFoundException;
import org.orient.bookstorebackend.model.request.UserRegisterRequest;
import org.orient.bookstorebackend.model.entity.User;
import org.orient.bookstorebackend.model.entity.UserDetails;
import org.orient.bookstorebackend.model.response.UserShortResponse;
import org.orient.bookstorebackend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    public UserShortResponse getUserByUsername(String username) {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        log.info("Fetched user: {}", user);

        return UserShortResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .build();
    }

    public UserShortResponse register(UserRegisterRequest userRegisterRequest) {
        var user = new User();

        var encryptedPassword = passwordEncoder.encode(userRegisterRequest.getPassword());

        user.setUsername(userRegisterRequest.getUsername());
        user.setPassword(encryptedPassword);

        var userDetails = new UserDetails();
        userDetails.setUser(user);
        user.setUserDetails(userDetails);

        var savedUser = userRepository.save(user);

        return UserShortResponse.builder()
                .id(savedUser.getId())
                .username(savedUser.getUsername())
                .build();
    }

    public UserShortResponse getUserById(Long userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return UserShortResponse.builder().id(user.getId()).username(user.getUsername()).build();
    }

}
