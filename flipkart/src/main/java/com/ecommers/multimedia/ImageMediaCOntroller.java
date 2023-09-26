package com.ecommers.multimedia;

import com.ecommers.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/imagenew")
public class ImageMediaCOntroller {
    @Autowired
    ImageServicess imageService;

    @PostMapping("/image")
    public ResponseEntity<?> upload(@RequestParam("multipartFile") MultipartFile multipartFile) throws IOException {
         String dov=imageService.upload(multipartFile);
         return ResponseEntity.status(HttpStatus.OK).body(dov);
    }
    @GetMapping("/view")
    public ResponseEntity<?> dowload(@RequestParam("name") String name) throws IOException {
       // byte [] image=imageService.download(name);
        ImageExample imageExample=imageService.download(name);
        String filepath=imageExample.getPath();
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf(imageExample.getType()))
                .body(Files.readAllBytes(new File(filepath).toPath()));
    }
}
