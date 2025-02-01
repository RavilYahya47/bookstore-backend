package org.orient.bookstorebackend.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.orient.bookstorebackend.model.entity.Book;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryService {

    public static void main(String[] args) {

        Book book = new Book();

        System.out.println(book.getId());

        book.getCategory().getName();

    }

}
