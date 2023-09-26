package com.ecommers.entities;

import com.ecommers.dto.ProfileDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;
import java.util.Optional;

@Entity
@Table(name="profile")
@Data
@NoArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    @NotNull
    private String name;
    @Column(name = "email")
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;
    @Column(name="password")
    private String password;
    @Column(name = "mobile")
    @Size(min = 10,max=10)
    private String mobile;
    @Column(name = "role")
    @NotNull
    private String role;
    @OneToMany(mappedBy = "profile")
    @JsonIgnore
    private List<Cart> carts;

    @OneToMany(mappedBy = "profile")
    @JsonIgnore
    private List<Order> orders;

    @OneToMany(mappedBy = "profile")

    @JsonIgnore
    private List<WishList> wishLists;

    @OneToMany
    @JsonIgnore
    private List<Payment> payments;

    public Profile(ProfileDto profileDto) {
        this.name = profileDto.getName();
        this.email = profileDto.getEmail();
        this.mobile = profileDto.getMobile();
        this.password=profileDto.getPassword();
        this.role=profileDto.getRole();
    }


}
