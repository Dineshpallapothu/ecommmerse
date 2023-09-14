package com.ecommers.serviceimpl;

import com.ecommers.dto.ItemDto;
import com.ecommers.dto.ProfileDto;
//import com.ecommers.entities.Cart;
//import com.ecommers.repositories.ProfileRepo;
import com.ecommers.entities.Item;
import com.ecommers.entities.Profile;
import com.ecommers.exceptions.InavalidDatagiven;
import com.ecommers.exceptions.MobileNumberInavalid;
import com.ecommers.exceptions.NoValueException;
import com.ecommers.exceptions.NothingFound;
import com.ecommers.repositories.ProfileRepo;
import com.ecommers.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    ProfileRepo profileRepo;
    Pattern pattern = Pattern.compile("(^$|[0-9]{10})");


    @Override
    public List<ProfileDto> getall() {
        List<Profile> profiles= profileRepo.findAll();
        List<ProfileDto> profileDtos=new ArrayList<>();
        if(profiles.isEmpty()){
            throw new NoValueException("NO Items present");
        }
        else {
            for(Profile profile:profiles){
                ProfileDto profileDto=new ProfileDto(profile);
                profileDtos.add(profileDto);
            }

        }

        return profileDtos;
    }

    @Override
    public ProfileDto getprofile(int id) {
        Profile profile=profileRepo.findById(id).orElseThrow(() ->  new NothingFound("no items available in cart on this id"));
        ProfileDto profileDto=new ProfileDto(profile);
        return profileDto;
    }

    @Override
    public ProfileDto remove(int id) {
        Profile profile =profileRepo.findById(id).orElseThrow(() ->  new NothingFound("no items available in cart on this id"))  ;
        ProfileDto profileDto=new ProfileDto(profile);
        profileRepo.delete(profile);

        return profileDto;
    }

    @Override
    public ProfileDto create(ProfileDto profileDto) {
        if(profileDto == null){
            throw new InavalidDatagiven("pleae give correct data");
        }
        else {
            if(pattern.matcher(profileDto.getMobile()).matches()){
                Profile profile=new Profile(profileDto);
                profileRepo.save(profile);
            }
            else {
                throw new MobileNumberInavalid("check mobile");
            }

        }

        return profileDto ;
    }

    @Override
    public ProfileDto update(int id,ProfileDto profileDto) {
        Profile profile=profileRepo.findById(id).orElseThrow(() ->  new NothingFound("no items available in cart on this id"));
        if(profile==null){
            throw new NoValueException("NO Items present");
        }else {
            profile.setName(profileDto.getName());
            profile.setEmail(profileDto.getEmail());
            profile.setMobile(profileDto.getMobile());
            profileRepo.save(profile);
        }
        return profileDto;
    }
}
