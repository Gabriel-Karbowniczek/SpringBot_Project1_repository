package com.example.demo.repository;

import com.example.demo.dto.Output;
import com.example.demo.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("SELECT new com.example.demo.dto.Output(author.name, book.name) FROM Author author join author.books book")
    public List<Output> getJoinInformation();
}
