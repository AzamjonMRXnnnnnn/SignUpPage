package com.example.SignUpPage.Service;

public interface ApplicationRegistrationService {
    Boolean registerUser(String username, String password);
    Boolean registerUserWithPhoneNumber(String phoneNumber);
}
