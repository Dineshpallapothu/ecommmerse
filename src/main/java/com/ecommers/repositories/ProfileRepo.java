package com.ecommers.repositories;

import com.ecommers.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepo extends JpaRepository<Profile,Integer> {
    Optional<Profile> findByEmail(String username);
}
