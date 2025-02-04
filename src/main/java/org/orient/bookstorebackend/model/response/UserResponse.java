package org.orient.bookstorebackend.model.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {

    String username;
    String fistName;
    String lastName;
    Integer age;
    List<String> addresses = new ArrayList<>();

}
