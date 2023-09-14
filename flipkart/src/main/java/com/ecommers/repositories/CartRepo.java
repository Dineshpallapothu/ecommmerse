package com.ecommers.repositories;

import com.ecommers.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepo extends JpaRepository<Cart,Integer> {
//    @Query(value = "select * from cart where item_id=:id ")
//    Cart finByItemId(@Param("id") int id);
   List<Cart> findByProfileid(int id);
   List<Cart> findByItemId(int id);
   List<Cart> findByItem_Category(String category);
  //  Cart ProfileId(int itemId);
}
