package com.example.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IsakhanKhangeldiBookDTO {
    private Long id;
    private String title;
    private String authorName;
    private String isbn;
    private Integer publicationYear;
    private Boolean available;
}