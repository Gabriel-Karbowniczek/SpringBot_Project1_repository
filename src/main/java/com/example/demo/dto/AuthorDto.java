package com.example.demo.dto;

import com.example.demo.entity.AuthorProfile;
import com.example.demo.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthorDto {
    private Long id;
    private String name;
    private AuthorProfileDto profile;

}

