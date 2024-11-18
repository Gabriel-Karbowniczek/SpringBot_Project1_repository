package com. example. demo. service;

import com.example.demo.dto.BookDto;
import com.example.demo.entity.Author;
import com.example.demo.entity.Book;
import com.example.demo.entity.Publisher;
import com.example.demo.mapper.BookMapper;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.PublisherRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    public List<BookDto> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(BookMapper::toDto)
                .collect(Collectors.toList());
    }

    public BookDto getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));
        return BookMapper.toDto(book);
    }

    public BookDto createBook(BookDto bookDto) {
        Author author = authorRepository.findById(bookDto.getAuthorId())
                .orElseThrow(() -> new EntityNotFoundException("Author not found"));
        List<Publisher> publisherList = publisherRepository.findAllById(bookDto.getPublisherIds());
        Set<Publisher> publishers = new HashSet<>(publisherList);

        Book book = BookMapper.toEntity(bookDto, author, publishers);
        book = bookRepository.save(book);
        return BookMapper.toDto(book);
    }

    public BookDto updateBook(Long id, BookDto bookDto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));
        Author author = authorRepository.findById(bookDto.getAuthorId())
                .orElseThrow(() -> new EntityNotFoundException("Author not found"));
        List<Publisher> publisherList = publisherRepository.findAllById(bookDto.getPublisherIds());
        Set<Publisher> publishers = new HashSet<>(publisherList);

        book.setName(bookDto.getName());
        book.setNumberOfPages(bookDto.getNumberOfPages());
        book.setYearOfPublication(bookDto.getYearOfPublication());
        book.setAuthor(author);
        book.setPublishers(publishers);

        book = bookRepository.save(book);
        return BookMapper.toDto(book);
    }

    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));
        bookRepository.delete(book);
    }
}

