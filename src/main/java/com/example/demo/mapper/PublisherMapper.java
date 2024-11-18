package com.example.demo.mapper;


import com.example.demo.dto.PublisherDto;
import com.example.demo.entity.Publisher;
import org.springframework.stereotype.Component;

@Component
public class PublisherMapper {
    public static PublisherDto toDto(Publisher publisher) {
        PublisherDto publisherDTO = new PublisherDto();
        publisherDTO.setId(publisher.getId());
        publisherDTO.setName(publisher.getName());
        publisherDTO.setEmail(publisher.getEmail());
        return publisherDTO;
    }

    public static Publisher toEntity(PublisherDto publisherDTO) {
        Publisher publisher = new Publisher();
        publisher.setId(publisherDTO.getId());
        publisher.setName(publisherDTO.getName());
        publisher.setEmail(publisherDTO.getEmail());
        return publisher;
    }
}
