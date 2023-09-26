package com.ecommers.multimedia;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageReposs extends JpaRepository<ImageExample, Integer>{
    Optional<ImageExample> findByName(String name);
}
