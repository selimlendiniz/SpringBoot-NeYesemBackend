package com.neyesem.neyesembackend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(

        @NotBlank(message = "Username cannot be blank")
        String username,
        @NotBlank(message = "Email cannot be blank")
        @Email(message = "Please enter a valid email")
        String email,

        String password,


        String firstName,


        String lastName

) {
}
