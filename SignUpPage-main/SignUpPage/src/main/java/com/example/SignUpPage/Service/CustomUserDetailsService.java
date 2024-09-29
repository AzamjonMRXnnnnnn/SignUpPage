package com.example.SignUpPage.Service;
import com.example.SignUpPage.Model.ApplicationUser;
//import com.example.SignUpPage.Model.CustomUserDetails;
import com.example.SignUpPage.Repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//public class CustomUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    ApplicationRepository repository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        ApplicationUser user =  repository.findByUsername(username);
//        if(user == null){
//            throw new UsernameNotFoundException("User not found");
//        }
//        return new CustomUserDetails(user);
//    }
//}
