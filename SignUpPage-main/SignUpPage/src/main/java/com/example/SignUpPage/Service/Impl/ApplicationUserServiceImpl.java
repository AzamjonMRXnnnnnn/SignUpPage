package com.example.SignUpPage.Service.Impl;
import com.example.SignUpPage.Model.ApplicationUser;
import com.example.SignUpPage.Repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserServiceImpl implements UserDetailsService {

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final ApplicationRepository applicationRepository;


    public ApplicationUserServiceImpl(BCryptPasswordEncoder passwordEncoder, ApplicationRepository applicationRepository) {
        this.passwordEncoder = passwordEncoder;
        this.applicationRepository = applicationRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("The username: " + username);

        ApplicationUser userOptional = applicationRepository.findByUsernameIfExists(username).orElseThrow();
        System.out.println("The taken User: " + userOptional);
        //return new ApplicationUser(userOptional.getId(), userOptional.getUsername(), userOptional.getPassword(), userOptional.getAuthorities());

        return applicationRepository.findByUsernameIfExists(username).orElseThrow(()-> new UsernameNotFoundException("user not found "));


    }
}
