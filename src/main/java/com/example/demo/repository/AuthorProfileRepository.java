package com.example.demo.repository;


import com.example.demo.entity.Author;
import com.example.demo.entity.AuthorProfile;
import com.example.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorProfileRepository extends JpaRepository<AuthorProfile, Long> {
    Optional<AuthorProfile> findByAuthor(Author author);
}
