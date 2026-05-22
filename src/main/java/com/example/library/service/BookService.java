package com.example.library.service;

import com.example.library.dto.IsakhanKhangeldiBookDTO;
import com.example.library.dto.IsakhanKhangeldiCreateBookRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    IsakhanKhangeldiBookDTO createBook(IsakhanKhangeldiCreateBookRequest request);
    IsakhanKhangeldiBookDTO getBookById(Long id);
    Page<IsakhanKhangeldiBookDTO> getAllBooks(Pageable pageable);
    Page<IsakhanKhangeldiBookDTO> searchBooks(String search, Pageable pageable);
    IsakhanKhangeldiBookDTO updateBook(Long id, IsakhanKhangeldiCreateBookRequest request);
    void deleteBook(Long id);
}