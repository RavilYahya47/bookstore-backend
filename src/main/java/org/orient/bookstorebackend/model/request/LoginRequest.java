package org.orient.bookstorebackend.model.request;

import lombok.Data;

@Data
public class LoginRequest {

    String username;
    String password;

}