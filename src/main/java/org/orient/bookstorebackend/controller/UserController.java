package org.orient.bookstorebackend.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.orient.bookstorebackend.model.dto.UserCreateDto;
import org.orient.bookstorebackend.model.response.UserDetailedResponse;
import org.orient.bookstorebackend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/users")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    UserService userService;

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody UserCreateDto user) {
        return ResponseEntity
                .status(201)
                .body(userService.registerUser(user));
    }

    @GetMapping("{id}/detailed")
    public ResponseEntity<UserDetailedResponse> getUserById(@PathVariable Long id){
        UserDetailedResponse detailedResponse = userService.getUserById(id);

        return new ResponseEntity<>(detailedResponse, HttpStatus.OK);

    }


}
