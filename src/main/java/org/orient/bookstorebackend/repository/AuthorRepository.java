package org.orient.bookstorebackend.repository;

import org.orient.bookstorebackend.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}