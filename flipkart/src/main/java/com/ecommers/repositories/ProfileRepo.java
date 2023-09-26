package com.ecommers.repositories;

import com.ecommers.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepo extends JpaRepository<Profile,Integer> {
    Optional<Profile> findByEmail(String username);
}
