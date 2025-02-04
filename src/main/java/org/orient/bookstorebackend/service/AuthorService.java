package org.orient.bookstorebackend.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.orient.bookstorebackend.exception.EntityNotFoundException;
import org.orient.bookstorebackend.model.dto.AuthorDto;
import org.orient.bookstorebackend.model.entity.Author;
import org.orient.bookstorebackend.repository.AuthorRepository;
import org.orient.bookstorebackend.util.mapper.AuthorMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthorService {

    AuthorRepository authorRepository;

    public AuthorDto.Response createAuthor(AuthorDto.CreateRequest request) {
        Author author = AuthorMapper.INSTANCE.toEntity(request);
        Author savedAuthor = authorRepository.save(author);
        return  AuthorMapper.INSTANCE.toResponse(savedAuthor);
    }

    public AuthorDto.Response getAuthor(Long id) {
        return authorRepository.findById(id)
                .map( AuthorMapper.INSTANCE::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Author not found"));
    }
}