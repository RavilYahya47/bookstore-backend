package org.orient.bookstorebackend.repository;

import org.orient.bookstorebackend.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
