package com.ecommers.serviceimpl;


import com.ecommers.Util.Util;
import com.ecommers.dto.CartDto;

import com.ecommers.entities.Cart;
import com.ecommers.entities.Item;
import com.ecommers.entities.Profile;
import com.ecommers.exceptions.InavalidDatagiven;
import com.ecommers.exceptions.NoValueException;
import com.ecommers.exceptions.NotFound;
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

import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    @Autowired
    Util util;


    @Override
    public List<CartDto> getall() {

        Profile profile = util.getCurrentUser();
        List<Cart> carts = cartRepo.findByProfile_Id(profile.getId());
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

        Profile profile = util.getCurrentUser();
        Cart cart = cartRepo.findById(id).orElseThrow(() -> new NotFound("no items available in cart on this id"));
        CartDto cartDto = new CartDto(cart);
        cartRepo.delete(cart);
        return cartDto;
    }

    @Override
    public List<CartDto> getcartbyprofile() {

        Profile profile = util.getCurrentUser();
        List<Cart> carts = cartRepo.findByProfile_Id(profile.getId());
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
        List<Cart> carts = cartRepo.findByItem_Id(id);
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
        Profile profile = util.getCurrentUser();
        Item item = itemRepo.findByName(cartDto.getName()).orElseThrow(() -> new NotFound("item not found"));
        Cart cart = new Cart(cartDto);
        cart.setProfile(profile);
        cart.getItem().setId(item.getId());
        cart.getItem().setId(item.getId());
        cartRepo.save(cart);
        return cartDto;
    }

    @Override
    public CartDto update(int id, CartDto cartDto) {
        Profile profile = util.getCurrentUser();
        Item item = itemRepo.findByName(cartDto.getName()).orElseThrow(() -> new NotFound("No cart items present on this id"));
        Cart cart = cartRepo.findById(id).orElseThrow(() -> new NotFound("No cart items present on this id"));
            cart.getItem().setId(item.getId());
            cart.setName(cartDto.getName());
            cart.setProfile(profile);
            cartRepo.save(cart);
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
            row1.createCell(1).setCellValue(cart.getItem().getId());
            row1.createCell(2).setCellValue(cart.getName());
            row1.createCell(3).setCellValue(cart.getProfile().getId());
            rowindex++;
        }
        ServletOutputStream servletOutputStream = response.getOutputStream();
        hssfWorkbook.write(servletOutputStream);


    }


}
