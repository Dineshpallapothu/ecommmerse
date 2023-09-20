package com.ecommers.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//@Entity
//@Table(name = "user_table")
public class User implements UserDetails {

    private String username;
    private String password;
    private List<GrantedAuthority> authorities;

    public User(Profile profile){
        this.username=profile.getEmail();
        this.password=profile.getPassword();
        this.authorities= Arrays.stream(profile.getRole().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)

}
