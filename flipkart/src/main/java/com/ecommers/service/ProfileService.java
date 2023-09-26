package com.ecommers.service;

import com.ecommers.dto.ItemDto;
import com.ecommers.dto.ProfileDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ProfileService {
    List<ProfileDto> getall();

    ProfileDto getprofile(int id);

    ProfileDto  remove(int id);

    ProfileDto create( ProfileDto profileDto);

    ProfileDto update( int id,ProfileDto cartDto);
}
