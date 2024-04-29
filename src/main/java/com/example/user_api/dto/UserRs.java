package com.example.user_api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserRs {
    private Long id;
    private String email;
    private String firstName;

    private String lastName;

    private LocalDate birthDate;
    private String address;
    private String phoneNumber;
}