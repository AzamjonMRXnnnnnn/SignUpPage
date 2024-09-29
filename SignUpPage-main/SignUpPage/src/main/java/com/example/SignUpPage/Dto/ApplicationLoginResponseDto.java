package com.example.SignUpPage.Dto;

import com.example.SignUpPage.Model.ApplicationUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationLoginResponseDto {
    private ApplicationUser user;
    private String jwt;

    public ApplicationLoginResponseDto(String token) {
        this.jwt = token;
    }
}
