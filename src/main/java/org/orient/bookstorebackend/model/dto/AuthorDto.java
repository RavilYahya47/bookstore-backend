package org.orient.bookstorebackend.model.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

public class AuthorDto {
    @FieldDefaults(level = AccessLevel.PRIVATE)

    public static class CreateRequest {

        @Valid
        @NotNull
        AuthorDetailsDto.CreateRequest details;

    }

    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Response {

        Long id;
        AuthorDetailsDto.Response details;

    }

}