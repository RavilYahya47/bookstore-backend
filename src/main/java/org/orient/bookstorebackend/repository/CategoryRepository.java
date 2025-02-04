package org.orient.bookstorebackend.repository;

import org.orient.bookstorebackend.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c WHERE c.active = true")
    List<Category> findAllActive();

    @Query("SELECT c FROM Category c")
    List<Category> findAllIncludingInactive();

    @Query("SELECT c FROM Category c WHERE c.id = :id")
    Optional<Category> findByIdIncludingInactive(@Param("id") Long id);

}