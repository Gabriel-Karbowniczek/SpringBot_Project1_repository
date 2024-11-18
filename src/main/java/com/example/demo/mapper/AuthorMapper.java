package com.example.demo.mapper;

import com.example.demo.dto.AuthorDto;
import com.example.demo.dto.AuthorProfileDto;
import com.example.demo.entity.Author;
import com.example.demo.entity.AuthorProfile;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    public static AuthorDto toDto(Author author) {
        if (author == null) {
            return null;
        }

        AuthorDto dto = new AuthorDto();
        dto.setId(author.getId());
        dto.setName(author.getName());

        if (author.getAuthorProfile() != null) {
            dto.setProfile(toProfileDto(author.getAuthorProfile()));
        }

        return dto;
    }

    public static Author toEntity(AuthorDto dto) {
        if (dto == null) {
            return null;
        }

        Author author = new Author();
        author.setId(dto.getId());
        author.setName(dto.getName());

        if (dto.getProfile() != null) {
            AuthorProfile profile = toProfileEntity(dto.getProfile());
            profile.setAuthor(author); // Ustawienie relacji dwukierunkowej
            author.setAuthorProfile(profile);
        }

        return author;
    }

    public static AuthorProfileDto toProfileDto(AuthorProfile profile) {
        if (profile == null) {
            return null;
        }

        AuthorProfileDto dto = new AuthorProfileDto();
        dto.setId(profile.getId());
        dto.setPlaceOfBirth(profile.getPlaceOfBirth());
        dto.setGender(profile.getGender());
        dto.setNationality(profile.getNationality());

        return dto;
    }

    public static AuthorProfile toProfileEntity(AuthorProfileDto dto) {
        if (dto == null) {
            return null;
        }

        AuthorProfile profile = new AuthorProfile();
        profile.setId(dto.getId());
        profile.setPlaceOfBirth(dto.getPlaceOfBirth());
        profile.setGender(dto.getGender());
        profile.setNationality(dto.getNationality());

        return profile;
    }
}



