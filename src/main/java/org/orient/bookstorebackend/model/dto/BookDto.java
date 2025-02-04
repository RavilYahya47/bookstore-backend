package org.orient.bookstorebackend.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Set;

public class BookDto {

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class CreateRequest {

        @NotBlank
        String title;
        String description;
        @NotNull
        BigDecimal price;
        @NotNull
        Long categoryId;
        Set<Long> authorIds;

    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class UpdateRequest {

        String title;
        String description;
        BigDecimal price;
        Long categoryId;
        Set<Long> authorIds;

    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Response {

        Long id;
        String title;
        String description;
        BigDecimal price;
        CategoryDto.Response category;
        Set<AuthorDto.Response> authors;

    }

}
