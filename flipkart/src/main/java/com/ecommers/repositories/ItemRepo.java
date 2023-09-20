package com.ecommers.repositories;

import com.ecommers.entities.Item;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepo extends JpaRepository<Item,Integer> {
    Optional<Item> findByName(String name);

    List<Item> findByCategory(String name);

    //Item findByName(String names);
}
