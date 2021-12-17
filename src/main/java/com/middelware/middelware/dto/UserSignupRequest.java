package com.middelware.middelware.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserSignupRequest {
    private  String firstName;
    private  String lastName;
    private  String sex;
    private  String email;
    private  String address;
    private Long cin ;
    private  String password;
    private Date birthday;
    private String phoneNumber;
}

