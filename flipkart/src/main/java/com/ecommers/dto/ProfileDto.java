package com.ecommers.dto;

import com.ecommers.entities.Profile;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProfileDto {
   // private int id;
    private String name;
    private String email;
    private String mobile;

    public ProfileDto(Profile profile) {
        this.name = profile.getName();
        this.email = profile.getEmail();
        this.mobile = profile.getMobile();
    }
}
