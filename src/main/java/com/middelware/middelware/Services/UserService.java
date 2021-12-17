package com.middelware.middelware.Services;

import com.middelware.middelware.Models.User;
import com.middelware.middelware.Repo.UserRepo;
import com.middelware.middelware.dto.UpdatePassword;
import com.middelware.middelware.dto.UpdateUser;

import com.middelware.middelware.dto.UserSignupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Component
@Transactional
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleService roleService;

    public User getUserById(Long id){return userRepo.findById(id).get();}
    public User getUserByEmail(String email){return  userRepo.getUserByEmail(email);}
    public User  createUser(UserSignupRequest userRequest) {
        User user = new User(
                null, userRequest.getFirstName(), userRequest.getLastName(), userRequest.getSex(),
                userRequest.getEmail(), userRequest.getAddress(), userRequest.getCin(), passwordEncoder.encode(userRequest.getPassword()), userRequest.getBirthday(),  userRequest.getPhoneNumber(),
                true,true, true, true,roleService.geRoleById(2L));
        System.out.println(user);
        return userRepo.save(user);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
    public User updateUser(UpdateUser updateUser){
        User user=getUserById(updateUser.getId());
        user.setFirstName(updateUser.getFirstName());
        user.setLastName(updateUser.getLastName());
        user.setAddress(updateUser.getAddress());
        user.setBirthday(updateUser.getBirthday());
        user.setCin(updateUser.getCin());
        user.setEmail(updateUser.getEmail());
        user.setPhoneNumber(updateUser.getPhoneNumber());
        user.setSex(updateUser.getSex());
        return userRepo.save(user);}
    public List<User> getUserByFirstName(String firstName){return userRepo.getUsersByFirstName(firstName);}
    public List<User>getUsersByLastName(String lastName){return userRepo.getUsersByLastName(lastName);}
    public User getUserByCin(Long cin){return userRepo.getUserByCin(cin);}
    public List<User>getCoaches(){
        List<User> coaches=new ArrayList<>();
        List<User> allUsers=getAllUsers();
        for (User user:allUsers){
            //if(user.hasRole("COACH")){
            if(user.getRole().equals(roleService.geRoleById(3L))){
                coaches.add(user);}
        }
        return coaches;}
    public void disableUserAccount(Long id) {
        User user=getUserById(id);
        user.setIsEnabled(false);
        userRepo.save(user);}
    public User makeCoach(Long userId){
        User user=getUserById(userId);
        user.setRole(roleService.geRoleById(3L));
        return userRepo.save(user);}
    public User makeUser(Long userId){
        User user=getUserById(userId);
        user.setRole(roleService.geRoleById(2L));
        return userRepo.save(user);}


    public User updatePassword(UpdatePassword updatePassword) {
        String password1=updatePassword.getPassword1();
        String password2=updatePassword.getPassword2();
        String actualPassword=updatePassword.getActualPassword();
        if (password1.equals(password2)){
            User user  = getUserById(updatePassword.getUserId());
            if (passwordEncoder.matches(user.getPassword(),actualPassword)){
                user.setPassword(passwordEncoder.encode(password1));
                return(userRepo.save(user));
            }
        }
        return null;}




}
