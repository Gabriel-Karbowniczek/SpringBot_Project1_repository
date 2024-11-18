package com.example.demo.service;

import com.example.demo.dto.AuthorDto;
import com.example.demo.dto.AuthorProfileDto;
import com.example.demo.entity.Author;
import com.example.demo.entity.AuthorProfile;
import com.example.demo.mapper.AuthorMapper;
import com.example.demo.repository.AuthorProfileRepository;
import com.example.demo.repository.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorProfileService {

    private final AuthorProfileRepository authorProfileRepository;
    private final AuthorRepository authorRepository;

    public AuthorProfileService(AuthorProfileRepository authorProfileRepository, AuthorRepository authorRepository) {
        this.authorProfileRepository = authorProfileRepository;
        this.authorRepository = authorRepository;
    }

    public AuthorProfileDto createProfile(Long authorId, AuthorProfileDto profileDto) {
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new EntityNotFoundException("Author not found"));

        AuthorProfile profile = AuthorMapper.toProfileEntity(profileDto);
        profile.setAuthor(author);

        AuthorProfile savedProfile = authorProfileRepository.save(profile);
        return AuthorMapper.toProfileDto(savedProfile);
    }

    public AuthorProfileDto updateProfile(Long authorId, AuthorProfileDto profileDto) {
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new EntityNotFoundException("Author not found"));

        AuthorProfile profileToUpdate = authorProfileRepository.findByAuthor(author)
                .orElseThrow(() -> new EntityNotFoundException("Profile not found"));

        profileToUpdate.setPlaceOfBirth(profileDto.getPlaceOfBirth());
        profileToUpdate.setGender(profileDto.getGender());
        profileToUpdate.setNationality(profileDto.getNationality());

        AuthorProfile updatedProfile = authorProfileRepository.save(profileToUpdate);
        return AuthorMapper.toProfileDto(updatedProfile);
    }

    public AuthorProfileDto getProfile(Long authorId) {
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new EntityNotFoundException("Author not found"));
        AuthorProfile profile = authorProfileRepository.findByAuthor(author)
                .orElseThrow(() -> new EntityNotFoundException("Profile not found"));

        return AuthorMapper.toProfileDto(profile);
    }

    public List<AuthorProfileDto> getAllProfiles() {
        List<AuthorProfile> profiles = authorProfileRepository.findAll();
        return profiles.stream()
                .map(AuthorMapper::toProfileDto)
                .collect(Collectors.toList());
    }

    public void deleteProfile(Long authorId) {
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new EntityNotFoundException("Author not found"));
        AuthorProfile profile = authorProfileRepository.findByAuthor(author)
                .orElseThrow(() -> new EntityNotFoundException("Profile not found"));

        authorProfileRepository.delete(profile);
    }
}
