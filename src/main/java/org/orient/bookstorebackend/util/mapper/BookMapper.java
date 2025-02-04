package org.orient.bookstorebackend.util.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.orient.bookstorebackend.exception.EntityNotFoundException;
import org.orient.bookstorebackend.model.dto.BookDto;
import org.orient.bookstorebackend.model.entity.Author;
import org.orient.bookstorebackend.model.entity.Book;
import org.orient.bookstorebackend.model.entity.Category;
import org.orient.bookstorebackend.repository.AuthorRepository;
import org.orient.bookstorebackend.repository.CategoryRepository;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class, AuthorMapper.class})
public interface BookMapper {


    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mapping(target = "category", ignore = true)
    @Mapping(target = "authors", ignore = true)
    Book toEntity(BookDto.CreateRequest dto);

    BookDto.Response toResponse(Book book);

    @AfterMapping
    default void mapRelations(BookDto.CreateRequest dto,
                              @MappingTarget Book book,
                              @Context CategoryRepository categoryRepo,
                              @Context AuthorRepository authorRepo) {
        Category category = categoryRepo.findById(dto.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
        book.setCategory(category);

        List<Author> authors = authorRepo.findAllById(dto.getAuthorIds());
        book.setAuthors(authors);

    }

    List<BookDto.Response> toResponseList(List<Book> books);

}