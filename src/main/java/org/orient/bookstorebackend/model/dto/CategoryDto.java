package org.orient.bookstorebackend.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryDto {

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class CreateRequest {

        @NotBlank
        String name;
        String description;

    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class UpdateRequest {

        String name;
        String description;

    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Response {

        Long id;
        String name;
        String description;

    }
}