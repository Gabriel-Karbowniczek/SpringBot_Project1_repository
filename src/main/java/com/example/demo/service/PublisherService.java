package com.example.demo.service;

import com.example.demo.dto.PublisherDto;
import com.example.demo.entity.Author;
import com.example.demo.entity.Book;
import com.example.demo.entity.Publisher;
import com.example.demo.mapper.PublisherMapper;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.PublisherRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PublisherService {

    private final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public List<PublisherDto> getAllPublishers() {
        return publisherRepository.findAll().stream()
                .map(PublisherMapper::toDto)
                .collect(Collectors.toList());
    }

    public PublisherDto getPublisherById(Long id) {
        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Publisher not found"));
        return PublisherMapper.toDto(publisher);
    }

    public PublisherDto createPublisher(PublisherDto publisherDto) {
        Publisher publisher = PublisherMapper.toEntity(publisherDto);
        publisher = publisherRepository.save(publisher);
        return PublisherMapper.toDto(publisher);
    }

    public PublisherDto updatePublisher(Long id, PublisherDto publisherDto) {
        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Publisher not found"));
        publisher.setName(publisherDto.getName());
        publisher.setEmail(publisherDto.getEmail());
        publisher = publisherRepository.save(publisher);
        return PublisherMapper.toDto(publisher);
    }

    public void deletePublisher(Long id) {
        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Publisher not found"));
        publisherRepository.delete(publisher);
    }
}
