package org.orient.bookstorebackend.util.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.orient.bookstorebackend.model.dto.AuthorDto;
import org.orient.bookstorebackend.model.entity.Author;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    public static final AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    @Mapping(target = "authorDetails", source = "details")
    Author toEntity(AuthorDto.CreateRequest request);

    AuthorDto.Response toResponse(Author author);

    @AfterMapping
    default void linkAuthorDetails(@MappingTarget Author author) {
        if (author.getAuthorDetails() != null) {
            author.getAuthorDetails().setAuthor(author);
        }
    }

}