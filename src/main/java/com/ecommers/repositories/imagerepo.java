package com.ecommers.repositories;

import com.ecommers.entities.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface imagerepo extends JpaRepository<ImageData, Integer> {
   Optional<ImageData> findByName(String name);
}
