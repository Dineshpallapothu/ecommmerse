package com.ecommers.serviceimpl;

import com.ecommers.dto.ProfileDto;
import com.ecommers.entities.Profile;
import com.ecommers.entities.User;
import com.ecommers.exceptions.NoValueException;
import com.ecommers.exceptions.NotFound;
import com.ecommers.repositories.ProfileRepo;
import com.ecommers.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ProfileServiceImpl implements ProfileService, UserDetailsService {
    @Autowired
    ProfileRepo profileRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public List<ProfileDto> getall() {
        List<Profile> profiles = profileRepo.findAll();
        List<ProfileDto> profileDtos = new ArrayList<>();
        if (profiles.isEmpty()) {
            throw new NoValueException("NO Items present");
        } else {
            for (Profile profile : profiles) {
                ProfileDto profileDto = new ProfileDto(profile);
                profileDtos.add(profileDto);
            }

        }

        return profileDtos;
    }

    @Override
    public ProfileDto getprofile(int id) {
        Profile profile = profileRepo.findById(id).orElseThrow(() -> new NotFound("no items available in cart on this id"));
        ProfileDto profileDto = new ProfileDto(profile);
        return profileDto;
    }

    @Override
    public ProfileDto remove(int id) {
        Profile profile = profileRepo.findById(id).orElseThrow(() -> new NotFound("no items available in cart on this id"));
        ProfileDto profileDto = new ProfileDto(profile);
        profileRepo.delete(profile);
        return profileDto;
    }

    @Override
    public ProfileDto create(ProfileDto profileDto) {
        Profile profile = new Profile(profileDto);
        profile.setPassword(passwordEncoder.encode(profile.getPassword()));
        profileRepo.save(profile);
        return profileDto;
    }

    @Override
    public ProfileDto update(int id, ProfileDto profileDto) {
        Profile profile = profileRepo.findById(id).orElseThrow(() -> new NotFound("no items available in cart on this id"));
            profile.setName(profileDto.getName());
            profile.setEmail(profileDto.getEmail());
            profile.setMobile(profileDto.getMobile());
            profileRepo.save(profile);
        return profileDto;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //load user from database
        Optional<Profile> user = profileRepo.findByEmail(username);
        return user.map(User::new).orElseThrow(() -> new UsernameNotFoundException("user Not found" + username));
    }

    public List<Profile> getusers() {
        List<Profile> profiles = profileRepo.findAll();
        return profiles;
    }
}
