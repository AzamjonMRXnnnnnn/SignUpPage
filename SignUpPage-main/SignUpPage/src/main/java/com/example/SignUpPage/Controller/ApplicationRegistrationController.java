package com.example.SignUpPage.Controller;
import com.example.SignUpPage.Dto.ApplicationLoginResponseDto;
import com.example.SignUpPage.Dto.ApplicationRegistrationDto;
import com.example.SignUpPage.Model.ApplicationUser;
import com.example.SignUpPage.Service.ApplicationRegistrationService;
import com.example.SignUpPage.Service.Impl.ApplicationAuthenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin("*")
public class ApplicationRegistrationController {

    @Autowired
    private ApplicationRegistrationService applicationRegistrationService;

    @Autowired
    private ApplicationAuthenticationServiceImpl applicationAuthenticationService;



    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody ApplicationRegistrationDto dto){
        //Boolean response = applicationRegistrationService.registerUser(dto.getUsername(), dto.getPassword());
        Boolean response = applicationRegistrationService.registerUserWithPhoneNumber(dto.getPhoneNumber());

        if(response){
            return ResponseEntity.ok("User added");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Registration failed with error:");
    }

    @PostMapping("/login")
    public ApplicationLoginResponseDto loginUser(@RequestBody ApplicationRegistrationDto body){
        return applicationAuthenticationService.loginUser(body.getUsername(), body.getPassword());
    }

}
