package com.middelware.middelware.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePassword {
    private Long userId;
    private String password1;
    private String password2;
    private String actualPassword;

}
