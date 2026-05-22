package com.example.library.controller;

import com.example.library.entity.IsakhanKhangeldiBorrowRecord;
import com.example.library.repository.IsakhanKhangeldiBorrowRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrows")
@RequiredArgsConstructor
public class IsakhanKhangeldiBorrowRecordController {

    private final IsakhanKhangeldiBorrowRecordRepository borrowRecordRepository;

    @GetMapping
    public List<IsakhanKhangeldiBorrowRecord> getAllBorrows() {
        return borrowRecordRepository.findAll();
    }

    @PostMapping
    public IsakhanKhangeldiBorrowRecord createBorrow(@RequestBody IsakhanKhangeldiBorrowRecord borrowRecord) {
        return borrowRecordRepository.save(borrowRecord);
    }
}