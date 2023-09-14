package com.ecommers.serviceimpl;



import com.ecommers.entities.User;
import com.ecommers.repositories.Userrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Base64;

@Service
public class CustomeUserService implements UserDetailsService {
    @Autowired
    private Userrepo userrepo;

   // private  Base64 base64;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //load user from database
        User user = userrepo.findByEmail(username).orElseThrow(() -> new RuntimeException("userName NOt Found"));

        return user;
    }
    public List<User> getusers(){
        List<User> users=userrepo.findAll();
//        List<User> users1=new ArrayList<>();
//       // Base64.Decoder decoder=
//        for (User user: users){
//            user.setPassword(Base64.getDecoder().toString());
//            users1.add(user);
//        }
        return users;
    }
    public User create(User user){

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userrepo.save(user);
    }

}
