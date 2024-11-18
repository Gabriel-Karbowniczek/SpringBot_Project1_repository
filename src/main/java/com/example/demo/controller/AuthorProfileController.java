package com.example.demo.controller;

import com.example.demo.dto.AuthorDto;
import com.example.demo.dto.AuthorProfileDto;
import com.example.demo.entity.Author;
import com.example.demo.entity.AuthorProfile;
import com.example.demo.repository.AuthorProfileRepository;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.service.AuthorProfileService;
import com.example.demo.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/authorProfile")
public class AuthorProfileController {

    private final AuthorProfileService authorProfileService;

    public AuthorProfileController(AuthorProfileService authorProfileService) {
        this.authorProfileService = authorProfileService;
    }

    @PostMapping("/{authorId}")
    public ResponseEntity<AuthorProfileDto> createProfile(@PathVariable Long authorId, @RequestBody AuthorProfileDto profileDto) {
        AuthorProfileDto createdProfile = authorProfileService.createProfile(authorId, profileDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProfile);
    }

    @PutMapping("/{authorId}")
    public ResponseEntity<AuthorProfileDto> updateProfile(@PathVariable Long authorId, @RequestBody AuthorProfileDto profileDto) {
        AuthorProfileDto updatedProfile = authorProfileService.updateProfile(authorId, profileDto);
        return ResponseEntity.ok(updatedProfile);
    }

    @GetMapping("/{authorId}")
    public ResponseEntity<AuthorProfileDto> getProfile(@PathVariable Long authorId) {
        AuthorProfileDto profile = authorProfileService.getProfile(authorId);
        return ResponseEntity.ok(profile);
    }

    @GetMapping
    public ResponseEntity<List<AuthorProfileDto>> getAllProfiles() {
        List<AuthorProfileDto> profiles = authorProfileService.getAllProfiles();
        return ResponseEntity.ok(profiles);
    }

    @DeleteMapping("/{authorId}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long authorId) {
        authorProfileService.deleteProfile(authorId);
        return ResponseEntity.noContent().build();
    }
}
