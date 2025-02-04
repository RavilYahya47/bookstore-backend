package org.orient.bookstorebackend.controller;

import jakarta.validation.Valid;
import org.orient.bookstorebackend.model.dto.AuthorDto;
import org.orient.bookstorebackend.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<AuthorDto.Response> createAuthor(
            @Valid @RequestBody AuthorDto.CreateRequest request) {
        return ResponseEntity.ok(authorService.createAuthor(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto.Response> getAuthor(@PathVariable Long id) {
        return ResponseEntity.ok(authorService.getAuthor(id));
    }

}