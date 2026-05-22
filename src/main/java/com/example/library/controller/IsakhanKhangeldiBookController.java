package com.example.library.controller;

import com.example.library.dto.IsakhanKhangeldiBookDTO;
import com.example.library.dto.IsakhanKhangeldiCreateBookRequest;
import com.example.library.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class IsakhanKhangeldiBookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<Page<IsakhanKhangeldiBookDTO>> getAllBooks(
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(bookService.getAllBooks(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<IsakhanKhangeldiBookDTO> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<IsakhanKhangeldiBookDTO>> searchBooks(
            @RequestParam(required = false) String search,
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {
        return ResponseEntity.ok(bookService.searchBooks(search, pageable));
    }

    @PostMapping
    public ResponseEntity<IsakhanKhangeldiBookDTO> createBook(@Valid @RequestBody IsakhanKhangeldiCreateBookRequest request) {
        IsakhanKhangeldiBookDTO created = bookService.createBook(request);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IsakhanKhangeldiBookDTO> updateBook(
            @PathVariable Long id,
            @Valid @RequestBody IsakhanKhangeldiCreateBookRequest request) {
        return ResponseEntity.ok(bookService.updateBook(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}