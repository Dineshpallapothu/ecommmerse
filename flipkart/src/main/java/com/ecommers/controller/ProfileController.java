package com.ecommers.controller;

import com.ecommers.dto.ProfileDto;
//import com.ecommers.entities.Profile;
//import com.ecommers.repositories.ProfileRepo;
import com.ecommers.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    @Autowired
    ProfileService profileService;

    @GetMapping("/all")
    public ResponseEntity<List<ProfileDto>> getall(){
        return ResponseEntity.status(HttpStatus.OK).body(profileService.getall());
    }
    @GetMapping("/profile")
    public ResponseEntity<ProfileDto> getprofile(@RequestParam("id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(profileService.getprofile(id));
    }
    @PostMapping("/create")
    public ResponseEntity<ProfileDto> create(@RequestBody ProfileDto profile){
        return ResponseEntity.status(HttpStatus.OK).body(profileService.create(profile));
    }
    @PutMapping("/update")
    public ResponseEntity<ProfileDto> update(@RequestParam("id") int id,@RequestBody ProfileDto profileDto){
        return ResponseEntity.status(HttpStatus.OK).body(profileService.update(id,profileDto));
    }
    @DeleteMapping("/remove")
    public ResponseEntity<ProfileDto> remove(@RequestParam("id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(profileService.remove(id));
    }
}
