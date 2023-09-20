package com.ecommers.repositories;

import com.ecommers.entities.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepo extends JpaRepository<WishList,Integer> {
}
