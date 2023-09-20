package com.ecommers.serviceimpl;

import com.ecommers.dto.CartDto;
import com.ecommers.dto.ItemDto;
//import com.ecommers.repositories.ItemRepo;
import com.ecommers.entities.Cart;
import com.ecommers.entities.Item;
import com.ecommers.exceptions.InavalidDatagiven;
import com.ecommers.exceptions.NoValueException;
import com.ecommers.exceptions.NothingFound;
import com.ecommers.repositories.ItemRepo;
import com.ecommers.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceimpl implements ItemService {
    @Autowired
    ItemRepo itemRepo;
    @Override
    public List<ItemDto> getall() {
        List<Item> items= itemRepo.findAll();
        List<ItemDto> itemDtos=new ArrayList<>();
        if(items.isEmpty()){
            throw new NoValueException("NO Items present");
        }
        else {
            for(Item item:items){
                ItemDto cartDto=new ItemDto(item);
                itemDtos.add(cartDto);
            }

        }

        return itemDtos;
    }

    @Override
    public ItemDto remove(int id) {
        Item item =itemRepo.findById(id).orElseThrow(() ->  new NothingFound("no items available in cart on this id"))  ;
        ItemDto itemDto=new ItemDto(item);
        itemRepo.delete(item);
        return itemDto;
    }

    @Override
    public ItemDto create(ItemDto itemDto) {
        if(itemDto == null){
            throw new InavalidDatagiven("pleae give correct data");
        }
        else {
            if(itemDto.getPrice()>0){
                Item item=new Item(itemDto);
                itemRepo.save(item);
            }
            else {
                System.out.println("sorry");
            }

        }
        return itemDto ;
    }

    @Override
    public List<ItemDto> getallbyName(String name) {
        Item items=itemRepo.findByName(name).orElseThrow(()->new NothingFound("items not found"));
        List<ItemDto> itemDtos=new ArrayList<>();
        if(items==null){
            throw new NoValueException("NO Items present");
        }
        else {
//            for(Item item:items){
                ItemDto itemDto=new ItemDto(items);
                itemDtos.add(itemDto);
          //  }

        }
        return itemDtos;
    }

    @Override
    public List<ItemDto> getllbyCat(String cat) {
        List<Item> items=itemRepo.findByCategory(cat);
        List<ItemDto> itemDtos=new ArrayList<>();
        if(items.isEmpty()){
            throw new NoValueException("NO Items present");
        }
        else {
            for(Item item:items){
                ItemDto cartDto=new ItemDto(item);
                itemDtos.add(cartDto);
            }

        }
        return itemDtos;
    }

    @Override
    public ItemDto update(int id,ItemDto itemDto) {
        Item item=itemRepo.findById(id).orElseThrow(() ->  new NothingFound("no items available in cart on this id"));
        if(item==null){
            throw new NoValueException("NO Items present");
        }else {
            item.setName(itemDto.getName());
            item.setCategory(itemDto.getCategory());
            itemRepo.save(item);
        }
        return itemDto;
    }
}
