package com.example.library.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "borrow_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IsakhanKhangeldiBorrowRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private IsakhanKhangeldiUser user;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private IsakhanKhangeldiBook book;

    @Column(nullable = false)
    private LocalDate borrowDate;

    private LocalDate returnDate;
}