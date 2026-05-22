package com.example.library.controller;

import com.example.library.entity.IsakhanKhangeldiPublisher;
import com.example.library.repository.IsakhanKhangeldiPublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publishers")
@RequiredArgsConstructor
public class IsakhanKhangeldiPublisherController {

    private final IsakhanKhangeldiPublisherRepository publisherRepository;

    @GetMapping
    public List<IsakhanKhangeldiPublisher> getAllPublishers() {
        return publisherRepository.findAll();
    }

    @PostMapping
    public IsakhanKhangeldiPublisher createPublisher(@RequestBody IsakhanKhangeldiPublisher publisher) {
        return publisherRepository.save(publisher);
    }
}