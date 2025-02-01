package org.orient.bookstorebackend.model.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryBooksDto {

    Long id;
    String name;
    List<BookDto> books;

}