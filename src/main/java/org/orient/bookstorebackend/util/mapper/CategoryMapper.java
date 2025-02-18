package org.orient.bookstorebackend.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.orient.bookstorebackend.model.dto.CategoryDto;
import org.orient.bookstorebackend.model.entity.Category;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    public static final CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category toEntity(CategoryDto.CreateRequest dto);

    CategoryDto.Response toResponse(Category category);

    List<CategoryDto.Response> toResponseList(List<Category> categories);


}