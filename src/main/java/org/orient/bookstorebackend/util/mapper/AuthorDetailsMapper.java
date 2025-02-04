package org.orient.bookstorebackend.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.orient.bookstorebackend.model.dto.AuthorDetailsDto;
import org.orient.bookstorebackend.model.entity.AuthorDetails;

@Mapper(componentModel = "spring")
public interface AuthorDetailsMapper {

    @Mapping(target = "author", ignore = true)
    AuthorDetails toEntity(AuthorDetailsDto.CreateRequest request);

    AuthorDetailsDto.Response toResponse(AuthorDetails authorDetails);

}