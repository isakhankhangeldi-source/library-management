package com.example.library.repository;

import com.example.library.entity.IsakhanKhangeldiBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IsakhanKhangeldiBookRepository extends JpaRepository<IsakhanKhangeldiBook, Long> {

    @Query("SELECT b FROM IsakhanKhangeldiBook b WHERE " +
            "LOWER(b.title) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(b.author) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<IsakhanKhangeldiBook> searchBooks(@Param("search") String search, Pageable pageable);
}