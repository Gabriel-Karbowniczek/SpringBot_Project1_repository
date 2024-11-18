package com.example.demo.mapper;


import com.example.demo.dto.BookDto;
import com.example.demo.entity.Author;
import com.example.demo.entity.Book;
import com.example.demo.entity.Publisher;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class BookMapper {
    public static BookDto toDto(Book book) {
        BookDto bookDTO = new BookDto();
        bookDTO.setId(book.getId());
        bookDTO.setName(book.getName());
        bookDTO.setNumberOfPages(book.getNumberOfPages());
        bookDTO.setYearOfPublication(book.getYearOfPublication());
        bookDTO.setAuthorId(book.getAuthor().getId());
        bookDTO.setPublisherIds(book.getPublishers().stream()
                .map(Publisher::getId)
                .collect(Collectors.toSet()));
        return bookDTO;
    }

    public static Book toEntity(BookDto bookDto, Author author, Set<Publisher> publishers) {
        Book book = new Book();
        book.setId(bookDto.getId());
        book.setName(bookDto.getName());
        book.setNumberOfPages(bookDto.getNumberOfPages());
        book.setYearOfPublication(bookDto.getYearOfPublication());
        book.setAuthor(author);
        book.setPublishers(publishers);
        return book;
    }
}
