package org.orient.bookstorebackend.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.orient.bookstorebackend.exception.EntityNotFoundException;
import org.orient.bookstorebackend.model.dto.BookDto;
import org.orient.bookstorebackend.model.entity.Author;
import org.orient.bookstorebackend.model.entity.Book;
import org.orient.bookstorebackend.model.entity.Category;
import org.orient.bookstorebackend.repository.AuthorRepository;
import org.orient.bookstorebackend.repository.BookRepository;
import org.orient.bookstorebackend.repository.CategoryRepository;
import org.orient.bookstorebackend.util.mapper.BookMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookService {

    BookRepository bookRepository;
    CategoryRepository categoryRepository;
    AuthorRepository authorRepository;

    public BookDto.Response createBook(BookDto.CreateRequest request) {
        Book book = BookMapper.INSTANCE.toEntity(request);
        resolveRelationships(book, request);
        return BookMapper.INSTANCE.toResponse(bookRepository.save(book));
    }

    public List<BookDto.Response> getAllBooks(boolean includeInactive) {
        return BookMapper.INSTANCE.toResponseList(
                includeInactive ? bookRepository.findAllIncludingInactive() : bookRepository.findAllActive()
        );
    }

    public BookDto.Response getBookById(Long id, boolean includeInactive) {
        Book book = includeInactive
                ? bookRepository.findByIdIncludingInactive(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"))
                : bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));
        return BookMapper.INSTANCE.toResponse(book);
    }

    public BookDto.Response updateBook(Long id, BookDto.UpdateRequest request) {
        Book book = bookRepository.findByIdIncludingInactive(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));

        updateBookFields(book, request);
        return BookMapper.INSTANCE.toResponse(bookRepository.save(book));
    }

    public void softDeleteBook(Long id) {
        Book book = bookRepository.findByIdIncludingInactive(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));
        book.setActive(false);
        bookRepository.save(book);
    }

    public void restoreBook(Long id) {
        Book book = bookRepository.findByIdIncludingInactive(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));
        book.setActive(true);
        bookRepository.save(book);
    }

    private void resolveRelationships(Book book, BookDto.CreateRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
        book.setCategory(category);

        List<Author> authors = authorRepository.findAllById(request.getAuthorIds());
        book.setAuthors(authors);
        authors.forEach(author -> author.getBooks().add(book));
    }

    private void updateBookFields(Book book, BookDto.UpdateRequest request) {
        if (request.getTitle() != null) book.setTitle(request.getTitle());
        if (request.getDescription() != null) book.setDescription(request.getDescription());
        if (request.getPrice() != null) book.setPrice(request.getPrice());

        if (request.getCategoryId() != null) {
            Category newCategory = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new EntityNotFoundException("Category not found"));
            Category oldCategory = book.getCategory();
            oldCategory.getBooks().remove(book);
            newCategory.getBooks().add(book);
            book.setCategory(newCategory);
        }

        if (request.getAuthorIds() != null) {
            List<Author> newAuthors = authorRepository.findAllById(request.getAuthorIds());
            updateAuthors(book, newAuthors);
        }
    }

    private void updateAuthors(Book book, List<Author> newAuthors) {
        Set<Author> authorsToRemove = new HashSet<>(book.getAuthors());
        newAuthors.forEach(authorsToRemove::remove);
        authorsToRemove.forEach(author -> author.getBooks().remove(book));

        Set<Author> authorsToAdd = new HashSet<>(newAuthors);
        book.getAuthors().forEach(authorsToAdd::remove);
        authorsToAdd.forEach(author -> author.getBooks().add(book));

        book.setAuthors(newAuthors);
        authorRepository.saveAll(authorsToRemove);
        authorRepository.saveAll(authorsToAdd);
    }

}