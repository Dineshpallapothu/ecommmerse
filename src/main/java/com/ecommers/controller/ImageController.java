package com.ecommers.controller;

import com.ecommers.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/image")
public class ImageController {
    @Autowired
    private ImageService imageService;
    @PostMapping("/image")
    public ResponseEntity<?> upload(@RequestParam("image")MultipartFile multipartFile) throws IOException {
       String upload =imageService.uploadimage(multipartFile);
        return ResponseEntity.status(HttpStatus.OK).body(upload);
    }
    @GetMapping("/name")
    public ResponseEntity<byte[]> download(@RequestParam("Name") String name){
        byte [] image=imageService.downloadtheimage(name);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
    }
}
