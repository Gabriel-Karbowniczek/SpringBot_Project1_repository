package com.example.demo.service;

import com.example.demo.dto.AuthorDto;
import com.example.demo.entity.Author;
import com.example.demo.entity.AuthorProfile;
import com.example.demo.mapper.AuthorMapper;
import com.example.demo.repository.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public AuthorDto createAuthor(AuthorDto authorDto) {
        Author author = AuthorMapper.toEntity(authorDto);
        Author savedAuthor = authorRepository.save(author);
        return AuthorMapper.toDto(savedAuthor);
    }

    public AuthorDto updateAuthor(Long id, AuthorDto authorDto) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (optionalAuthor.isEmpty()) {
            throw new EntityNotFoundException("Author not found");
        }

        Author authorToUpdate = optionalAuthor.get();
        authorToUpdate.setName(authorDto.getName());

        if (authorDto.getProfile() != null) {
            AuthorProfile profile = AuthorMapper.toProfileEntity(authorDto.getProfile());
            profile.setAuthor(authorToUpdate);
            authorToUpdate.setAuthorProfile(profile);
        }

        Author updatedAuthor = authorRepository.save(authorToUpdate);
        return AuthorMapper.toDto(updatedAuthor);
    }

    public AuthorDto getAuthor(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Author not found"));
        return AuthorMapper.toDto(author);
    }

    public List<AuthorDto> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream()
                .map(AuthorMapper::toDto)
                .collect(Collectors.toList());
    }

    public void deleteAuthor(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new EntityNotFoundException("Author not found");
        }
        authorRepository.deleteById(id);
    }
}


