package com.middelware.middelware.Authentification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthentificationRequest {
    private String userName;
    private String password;
}
