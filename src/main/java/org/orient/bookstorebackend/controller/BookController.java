package org.orient.bookstorebackend.controller;


import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.orient.bookstorebackend.model.dto.BookDto;
import org.orient.bookstorebackend.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/books")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookController {

    BookService bookService;

    @PostMapping
    public ResponseEntity<BookDto.Response> createBook(@Valid @RequestBody BookDto.CreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.createBook(request));
    }

    @GetMapping
    public ResponseEntity<List<BookDto.Response>> getAllBooks(
            @RequestParam(defaultValue = "false") boolean includeInactive) {
        return ResponseEntity.ok(bookService.getAllBooks(includeInactive));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto.Response> getBookById(
            @PathVariable Long id,
            @RequestParam(defaultValue = "false") boolean includeInactive) {
        return ResponseEntity.ok(bookService.getBookById(id, includeInactive));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto.Response> updateBook(
            @PathVariable Long id,
            @Valid @RequestBody BookDto.UpdateRequest request) {
        return ResponseEntity.ok(bookService.updateBook(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.softDeleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/restore")
    public ResponseEntity<Void> restoreBook(@PathVariable Long id) {
        bookService.restoreBook(id);
        return ResponseEntity.ok().build();
    }

}