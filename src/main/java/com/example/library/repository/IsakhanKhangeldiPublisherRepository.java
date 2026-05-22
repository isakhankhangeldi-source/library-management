package com.example.library.repository;

import com.example.library.entity.IsakhanKhangeldiPublisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IsakhanKhangeldiPublisherRepository extends JpaRepository<IsakhanKhangeldiPublisher, Long> {
}