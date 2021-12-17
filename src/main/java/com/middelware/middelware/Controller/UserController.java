package com.middelware.middelware.Controller;

import com.middelware.middelware.Models.User;
import com.middelware.middelware.Services.UserService;
import com.middelware.middelware.dto.UpdatePassword;
import com.middelware.middelware.dto.UpdateUser;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;


@RestController
@RequestMapping("api/User")
@AllArgsConstructor
@Transactional
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
    private UserService userService;
    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("")
    public List<User>getAllUsers(){return userService.getAllUsers();}
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id){return userService.getUserById(id);}
    @GetMapping("/mail/{mail}")
    public User getUserByEmail(@PathVariable String mail){return userService.getUserByEmail(mail);}
    @GetMapping("/firstName/{firstName}")
    public List<User> getUserByFirstName(@PathVariable String firstName){return userService.getUserByFirstName(firstName);}
    @GetMapping("/lastName/{lastName}")
    public List<User> getUserByLastName(@PathVariable String lastName){return userService.getUsersByLastName(lastName);}
    @GetMapping("/cin/{cin}")
    public User getUserByCin(@PathVariable Long cin){return userService.getUserByCin(cin);}
    @GetMapping("/coaches")
    public List<User> getCoaches(){return userService.getCoaches();}



    @PutMapping("")
    public User updateUser(@RequestBody UpdateUser updateUser){return userService.updateUser(updateUser);}

    //@PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/disableAccount/{userId}")
    public void disableAccount(@PathVariable Long userId){userService.disableUserAccount(userId);}

    //@PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/makeCoach/{userId}")
    public void makeCoach(@PathVariable Long userId){userService.makeCoach(userId);}

    //@PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/makeUser/{userId}")
    public void makeUser(@PathVariable Long userId){userService.makeUser(userId);}

    @PutMapping("/updatePassword")
    public User updatePassword(@RequestBody UpdatePassword updatePassword){
        return userService.updatePassword(updatePassword);}


}
