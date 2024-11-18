package com.example.demo.dto;

import com.example.demo.entity.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthorProfileDto {
    private Long id;
    private String placeOfBirth;
    private String gender;
    private String nationality;
}