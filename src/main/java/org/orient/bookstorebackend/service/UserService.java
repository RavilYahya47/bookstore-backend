package org.orient.bookstorebackend.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.orient.bookstorebackend.model.dto.UserCreateDto;
import org.orient.bookstorebackend.model.response.UserDetailedResponse;
import org.orient.bookstorebackend.model.response.UserShortResponse;
import org.orient.bookstorebackend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    UserRepository userRepository;
    RestTemplate restTemplate;

    public UserShortResponse registerUser(UserCreateDto userCreateDto) {
        var response = restTemplate.postForEntity(
                "http://localhost:8081/v1/users",
                userCreateDto,
                UserShortResponse.class);

        var userShortResponse = response.getBody();

        log.info("Registered user: {}", userShortResponse);

        return userShortResponse;
    }

    public UserDetailedResponse getUserById(Long id) {

        String uri = "http://localhost:8081/v1/users/" + id +"/detailed";

        var response  = restTemplate.getForObject(
                uri,
                UserDetailedResponse.class
        );

        log.info("getUserById: {}", response);

        return response;
    }

}
