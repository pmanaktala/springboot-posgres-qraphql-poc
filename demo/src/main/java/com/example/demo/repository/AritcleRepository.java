package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Author;

public interface AritcleRepository extends JpaRepository<Author, Long> {

	Optional<Author> findById(Long id);
}
