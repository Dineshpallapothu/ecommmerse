package com.ecommers.dto;

import com.ecommers.entities.Profile;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProfileDto {
    private int id;
    @NotBlank
    private String name;
    @NotBlank
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;
    @NotBlank
    @Pattern(regexp = "(^$|[0-9]{10})")
    @Size(min = 10,max=10)
    private String mobile;
    @NotBlank
   private String role;
    @NotBlank
   private String password;
    public ProfileDto(Profile profile) {
        this.id=profile.getId();
        this.name = profile.getName();
        this.email = profile.getEmail();
        this.mobile = profile.getMobile();
        this.password=profile.getPassword();
        this.role=profile.getRole();
    }
}
