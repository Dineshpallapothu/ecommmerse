package com.ecommers.dto;

//import com.ecommers.entities.Item;
import com.ecommers.entities.Item;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemDto {
  //  private  int id;
    private String name;
    private String Category;

    public ItemDto(Item item) {
        this.name =item.getName();
        Category = item.getCategory();
    }
}
