package org.orient.bookstorebackend.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.orient.bookstorebackend.exception.EntityNotFoundException;
import org.orient.bookstorebackend.model.dto.CategoryDto;
import org.orient.bookstorebackend.model.entity.Category;
import org.orient.bookstorebackend.repository.CategoryRepository;
import org.orient.bookstorebackend.util.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryService {

    CategoryRepository categoryRepository;

    public CategoryDto.Response createCategory(CategoryDto.CreateRequest request) {
        Category category = CategoryMapper.INSTANCE.toEntity(request);
        return CategoryMapper.INSTANCE.toResponse(categoryRepository.save(category));
    }

    public List<CategoryDto.Response> getAllCategories(boolean includeInactive) {
        return CategoryMapper.INSTANCE.toResponseList(
                includeInactive
                        ? categoryRepository.findAllIncludingInactive()
                        : categoryRepository.findAllActive()
        );
    }

    public CategoryDto.Response getCategoryById(Long id, boolean includeInactive) {
        Category category = includeInactive
                ? categoryRepository.findByIdIncludingInactive(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"))
                : categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
        return CategoryMapper.INSTANCE.toResponse(category);
    }

    public CategoryDto.Response updateCategory(Long id, CategoryDto.UpdateRequest request) {
        Category category = categoryRepository.findByIdIncludingInactive(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        if (request.getName() != null) category.setName(request.getName());
        if (request.getDescription() != null) category.setDescription(request.getDescription());

        return CategoryMapper.INSTANCE.toResponse(categoryRepository.save(category));
    }

    public void softDeleteCategory(Long id) {
        Category category = categoryRepository.findByIdIncludingInactive(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
        category.setActive(false);
        categoryRepository.save(category);
    }

    public void restoreCategory(Long id) {
        Category category = categoryRepository.findByIdIncludingInactive(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
        category.setActive(true);
        categoryRepository.save(category);
    }

}
