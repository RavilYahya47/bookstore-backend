package org.orient.bookstorebackend.model.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookDto {

    Long id;
    String title;
    String description;
    BigDecimal price;
    Boolean active;

}
