package com.example.library.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IsakhanKhangeldiCreateBookRequest {

    @NotBlank(message = "Title is required")
    private String title;

    @NotNull(message = "Author ID is required")  // ← теперь это ID автора
    private Long authorId;                      // ← было String author

    @NotBlank(message = "ISBN is required")
    @Size(min = 10, max = 13, message = "ISBN must be 10 or 13 characters")
    private String isbn;

    @NotNull(message = "Publication year is required")
    private Integer publicationYear;
}