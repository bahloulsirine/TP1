package com.middelware.middelware.Authentification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthentificationResponse {
    private  String jwt ;
    private UserDetails user ;
}
