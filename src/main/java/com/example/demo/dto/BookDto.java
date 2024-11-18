package com.example.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookDto {
    private Long id;
    private String name;
    private Integer numberOfPages;
    private Integer yearOfPublication;
    private Long authorId;
    private Set<Long> publisherIds;
}
