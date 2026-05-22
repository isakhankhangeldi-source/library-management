package com.example.library.controller;

import com.example.library.entity.IsakhanKhangeldiAuthor;
import com.example.library.repository.IsakhanKhangeldiAuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class IsakhanKhangeldiAuthorController {

    private final IsakhanKhangeldiAuthorRepository authorRepository;

    @GetMapping
    public List<IsakhanKhangeldiAuthor> getAllAuthors() {
        return authorRepository.findAll();
    }

    @PostMapping
    public IsakhanKhangeldiAuthor createAuthor(@RequestBody IsakhanKhangeldiAuthor author) {
        return authorRepository.save(author);
    }
}