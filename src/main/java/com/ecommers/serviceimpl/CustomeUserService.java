//package com.ecommers.serviceimpl;
//
//
//
//import com.ecommers.entities.Profile;
//import com.ecommers.entities.User;
//import com.ecommers.repositories.ProfileRepo;
//import com.ecommers.repositories.Userrepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.*;
//
//@Service
//public class CustomeUserService implements UserDetailsService {
//    @Autowired
//    private ProfileRepo profileRepo;
//
//   // private  Base64 base64;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        //load user from database
//        Optional<Profile> user = profileRepo.findByEmail(username);
//
//        return user.map(User::new).orElseThrow(()->new UsernameNotFoundException("user Not found"+username));
//    }
//    public List<Profile> getusers(){
//        List<Profile> users=profileRepo.findAll();
////        List<User> users1=new ArrayList<>();
////       // Base64.Decoder decoder=
////        for (User user: users){
////            user.setPassword(Base64.getDecoder().toString());
////            users1.add(user);
////        }
//        return users;
//    }
//    public Profile create(Profile user){
//
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        return profileRepo.save(user);
//    }
//
//}
