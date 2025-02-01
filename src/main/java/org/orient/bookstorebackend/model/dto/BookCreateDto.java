package org.orient.bookstorebackend.model.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookCreateDto {

    String title;
    String description;
    BigDecimal price;

}
