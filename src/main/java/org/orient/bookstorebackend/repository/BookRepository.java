package org.orient.bookstorebackend.repository;

import org.orient.bookstorebackend.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE b.active = true")
    List<Book> findAllActive();

    @Query("SELECT b FROM Book b")
    List<Book> findAllIncludingInactive();

    @Query("SELECT b FROM Book b WHERE b.id = :id")
    Optional<Book> findByIdIncludingInactive(@Param("id") Long id);

}
