package org.orient.bookstorebackend.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

public class AuthorDetailsDto {

    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class CreateRequest {

        @NotBlank
        @Size(max = 128)
        String name;
        @Size(max = 4096)
        String bio;

    }

    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Response {

        String name;
        String bio;

    }
}