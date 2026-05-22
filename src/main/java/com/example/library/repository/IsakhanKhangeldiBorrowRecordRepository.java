package com.example.library.repository;

import com.example.library.entity.IsakhanKhangeldiBorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IsakhanKhangeldiBorrowRecordRepository extends JpaRepository<IsakhanKhangeldiBorrowRecord, Long> {
}