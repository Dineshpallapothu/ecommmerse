package com.ecommers.service;

import com.ecommers.entities.ImageData;
import com.ecommers.multi.utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecommers.repositories.imagerepo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageService {
    @Autowired
    private imagerepo imagerepo;
   // String path="C:\\dinesh\\";
    public String uploadimage(MultipartFile multipartFile) throws IOException {
       // String path1=path+multipartFile.getOriginalFilename();
     ImageData imageData=   imagerepo.save(ImageData.builder().name(multipartFile.getOriginalFilename())
                .type(multipartFile.getContentType())
                .imagedata(utils.compressImage(multipartFile.getBytes())).build()
        );
     //multipartFile.transferTo();
        if(imageData!=null){
           return "success"+multipartFile.getOriginalFilename();
        }
        else {
            return "failed to upload";
        }
    }
    public byte[] downloadtheimage(String filename){
       Optional<ImageData> imageData= imagerepo.findByName(filename);
       byte[] imgaes= utils.decompressImage(imageData.get().getImagedata());
       return imgaes;
    }
}
