package org.orient.bookstorebackend.model.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateDto {

    String username;
    String password;
    String fistName;
    String lastName;
    Integer age;

}
