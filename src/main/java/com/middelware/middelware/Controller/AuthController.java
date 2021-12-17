package com.middelware.middelware.Controller;

import com.middelware.middelware.Authentification.AuthentificationRequest;
import com.middelware.middelware.Authentification.AuthentificationResponse;
import com.middelware.middelware.Models.User;
import com.middelware.middelware.MyUserDetailsService;
import com.middelware.middelware.Services.UserService;
import com.middelware.middelware.Util.JwtUtil;
import com.middelware.middelware.dto.UserSignupRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@AllArgsConstructor
@RestController
@Transactional
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping("api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService ;
    private final MyUserDetailsService userDetailsService;
    private  final JwtUtil jwtTokenUtil;

    @PostMapping("/authentication")
    public ResponseEntity<AuthentificationResponse> createAuthenticationToken(@RequestBody AuthentificationRequest authenticationRequest)throws Exception{
        try { authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(),authenticationRequest.getPassword())
        );
        }catch (BadCredentialsException e){
            throw new Exception("Incorrect username or password",e );
        }
        final UserDetails userDetails= userDetailsService
                .loadUserByUsername(authenticationRequest.getUserName());
        final String jwt=jwtTokenUtil.generateToken(userDetails);
        return new  ResponseEntity<>(new AuthentificationResponse(jwt,userDetails), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public User signUp(@RequestBody UserSignupRequest userSignupRequest){
        System.out.println(userSignupRequest);
        return  userService.createUser(userSignupRequest);
    }
    @GetMapping("/profile")
    public User getCurrentUser(){
        System.out.println("************************************");
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }


}
