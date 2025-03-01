package org.orient.bookstorebackend.repository;

import org.orient.bookstorebackend.model.entity.AuthorDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorDetailsRepository extends JpaRepository<AuthorDetails, Long> {
}
