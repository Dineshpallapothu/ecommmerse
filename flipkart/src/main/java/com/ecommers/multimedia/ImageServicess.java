package com.ecommers.multimedia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.SecureRandom;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ImageServicess {
    @Autowired
    private ImageReposs imageReposs;

    String path="C:\\dinesh\\";
    public String upload(MultipartFile multipartFile) throws IOException {
        String path1=path+ multipartFile.getOriginalFilename();
       ImageExample imageExample= imageReposs.save(ImageExample.builder().path(path1).name(multipartFile.getOriginalFilename()).type(multipartFile.getContentType()).build());
        multipartFile.transferTo(new File(path1));//it will transfer the file into the path folder  
        if(imageReposs!=null){//check the conditions
            return "success";
        }
        else {
            return "failed";
        }
    }
    public ImageExample download(String name) throws IOException {
      Optional<ImageExample> imageExample= imageReposs.findByName(name);
//      String image=imageExample.get().getPath();
//      byte[] images= Files.readAllBytes(new File(image).toPath());
      return imageExample.get();
    }
}
