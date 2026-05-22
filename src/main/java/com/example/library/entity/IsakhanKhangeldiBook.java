package com.example.library.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IsakhanKhangeldiBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(unique = true, nullable = false)
    private String isbn;

    private Integer publicationYear;

    private Boolean available = true;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private IsakhanKhangeldiAuthor author;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private IsakhanKhangeldiCategory category;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private IsakhanKhangeldiPublisher publisher;
}