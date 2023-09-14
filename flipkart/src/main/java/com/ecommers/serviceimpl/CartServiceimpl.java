package com.ecommers.serviceimpl;

import com.ecommers.dto.CartDto;

import com.ecommers.entities.Cart;
import com.ecommers.entities.Item;
import com.ecommers.entities.Profile;
import com.ecommers.entities.User;
import com.ecommers.exceptions.InavalidDatagiven;
import com.ecommers.exceptions.NoValueException;
import com.ecommers.exceptions.NothingFound;
import com.ecommers.repositories.CartRepo;
import com.ecommers.repositories.ItemRepo;
import com.ecommers.repositories.ProfileRepo;
import com.ecommers.service.CartService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceimpl implements CartService {
    @Autowired
    CartRepo cartRepo;
    @Autowired
    ProfileRepo profileRepo;
    @Autowired
    ItemRepo itemRepo;

    @Override
    public List<CartDto> getall() {
        List<Cart> carts = cartRepo.findAll();
        List<CartDto> cartDtos = new ArrayList<>();
        if (carts.isEmpty()) {
            throw new NoValueException("NO Items present");
        } else {
            for (Cart cart : carts) {
                CartDto cartDto = new CartDto(cart);
                cartDtos.add(cartDto);
            }
        }

        return cartDtos;
    }

    @Override
    public CartDto remove(int id) {
        Cart cart = cartRepo.findById(id).orElseThrow(() -> new NothingFound("no items available in cart on this id"));
        CartDto cartDto = new CartDto(cart);
        cartRepo.delete(cart);
        return cartDto;
    }

    @Override
    public List<CartDto> getcartbyprofile(int id) {
        List<Cart> carts = cartRepo.findByProfileid(id);
        List<CartDto> cartDtos = new ArrayList<>();
        if (carts.isEmpty()) {
            throw new NoValueException("NO Items present");
        } else {
            for (Cart cart : carts) {
                CartDto cartDto = new CartDto(cart);
                cartDtos.add(cartDto);
            }
        }
        return cartDtos;
    }

    @Override
    public List<CartDto> getcartbyitem(int id) {
        List<Cart> carts = cartRepo.findByItemId(id);
        List<CartDto> cartDtos = new ArrayList<>();
        if (carts.isEmpty()) {
            throw new NoValueException("NO Items present");
        } else {
            for (Cart cart : carts) {
                CartDto cartDto = new CartDto(cart);
                cartDtos.add(cartDto);
            }
        }
        return cartDtos;
    }

    @Override
    public List<CartDto> getcartbycategory(String name) {
        List<Cart> carts = cartRepo.findByItem_Category(name);
        List<CartDto> cartDtos = new ArrayList<>();
        if (carts.isEmpty()) {
            throw new NoValueException("NO Items present");
        } else {
            for (Cart cart : carts) {
                CartDto cartDto = new CartDto(cart);
                cartDtos.add(cartDto);
            }
        }
        return cartDtos;
    }

    @Override
    public CartDto create(CartDto cartDto) {
        Profile profile = profileRepo.findById(cartDto.getProfileId()).orElseThrow(() -> new NothingFound("profile id not found can you check it"));
        Item item = itemRepo.findById(cartDto.getItemId()).orElseThrow(() -> new NothingFound("item id not found can you check it"));
        if (cartDto == null) {
            throw new InavalidDatagiven("pleae give correct data");
        } else {
            Cart cart = new Cart(cartDto);
            cartRepo.save(cart);
        }

        return cartDto;
    }

    @Override
    public CartDto update(int id, CartDto cartDto) {
        Cart cart = cartRepo.findById(id).orElseThrow(() -> new NothingFound("No cart items present on this id"));
        if (cart == null) {
            throw new NoValueException("NO Items present");
        } else {
            cart.setItemId(cartDto.getItemId());
            cart.setName(cartDto.getName());
            cart.setProfileid(cartDto.getProfileId());
            cartRepo.save(cart);
        }
        return cartDto;
    }

    @Override
    public void generateExcel(HttpServletResponse response) throws IOException {
        List<Cart> carts = cartRepo.findAll();
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet sheet = hssfWorkbook.createSheet("cart details");
        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("id");
        row.createCell(1).setCellValue("item Id");
        row.createCell(2).setCellValue("item name");
        row.createCell(3).setCellValue("profile id");
        int rowindex = 1;
        for (Cart cart : carts) {
            HSSFRow row1 = sheet.createRow(rowindex);
            row1.createCell(0).setCellValue(cart.getId());
            row1.createCell(1).setCellValue(cart.getItemId());
            row1.createCell(2).setCellValue(cart.getName());
            row1.createCell(3).setCellValue(cart.getProfileid());
            rowindex++;
        }
        ServletOutputStream servletOutputStream = response.getOutputStream();
        hssfWorkbook.write(servletOutputStream);


    }
    //public List<Cart>

}
