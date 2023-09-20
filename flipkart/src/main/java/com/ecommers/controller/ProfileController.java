package com.ecommers.controller;

import com.ecommers.dto.JwtRequest;
import com.ecommers.dto.JwtResponse;
import com.ecommers.dto.ProfileDto;
//import com.ecommers.entities.Profile;
//import com.ecommers.repositories.ProfileRepo;
import com.ecommers.jwt.JwtHelper;
import com.ecommers.service.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    @Autowired
    private JwtHelper helper;

    private Logger logger = LoggerFactory.getLogger(ProfileController.class);
    @Autowired
    ProfileService profileService;

    @GetMapping("/all")
    public ResponseEntity<List<ProfileDto>> getall() {
        return ResponseEntity.status(HttpStatus.OK).body(profileService.getall());
    }

    @GetMapping("/profile")
    public ResponseEntity<ProfileDto> getprofile(@RequestParam("id") int id) {
        return ResponseEntity.status(HttpStatus.OK).body(profileService.getprofile(id));
    }

    //    @PostMapping("/create")
//    public ResponseEntity<ProfileDto> create(@RequestBody ProfileDto profile){
//        return ResponseEntity.status(HttpStatus.OK).body(profileService.create(profile));
//    }
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;


    //    @Autowired
//    private CustomeUserService userService;


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

        this.doAuthenticate(request.getEmail(), request.getPassword());


        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);

        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid Username or Password  !!");
        }

    }

    @PutMapping("/update")
    public ResponseEntity<ProfileDto> update(@RequestParam("id") int id, @RequestBody ProfileDto profileDto) {
        return ResponseEntity.status(HttpStatus.OK).body(profileService.update(id, profileDto));
    }

    @DeleteMapping("/remove")
    public ResponseEntity<ProfileDto> remove(@RequestParam("id") int id) {
        return ResponseEntity.status(HttpStatus.OK).body(profileService.remove(id));
    }

    @PostMapping("/create")
    public ProfileDto cretaeuser(@RequestBody ProfileDto user) {
        return profileService.create(user);
    }
}
