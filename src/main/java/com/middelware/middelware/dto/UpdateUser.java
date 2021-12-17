package com.middelware.middelware.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateUser {
    private Long id;
    private Long userId;
    private  String firstName;
    private  String lastName;
    private  String sex;
    private  String email;
    private Long cin ;
    private String phoneNumber;
    private  String address;
    private Date birthday;
}