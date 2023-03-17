package com.assignment.LoginLogout.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//@RestController tells Spring this will be used to control API functionality and requests sent to our prog
@RestController
public class UserController {

    //@Autowired handles code injection so no need to set up constructor
    @Autowired
    UserRepository userRepository;

    //@Valid requires valid json obj similar to User class to ensure obj received is usable in our prog
    @CrossOrigin
    @PostMapping("/users/register")
    public String registerUser(@Valid @RequestBody User newUser){
        List<User> users = userRepository.findAll();
        if (users.contains(newUser)) {
            System.out.println("User already exists!");
            return "User already exists!";
        }
        userRepository.save(newUser);
        return "SUCCESS";
    }
    @CrossOrigin
    @PostMapping("/users/login")
    public String loginUser(@Valid @RequestBody User user){
        List<User> users = userRepository.findAll();
        for (User u : users) {
            if (u.equals(user)) {
                user.setLoggedIn(true);
                userRepository.save(user);
                return user.getUsername();
            }
        }
        return "FAILURE";
    }
    @CrossOrigin
    @PostMapping("/users/logout")
    public String logoutUser(@Valid @RequestBody String username) {
        List<User> users = userRepository.findAll();

        for (User u : users) {
            if (u.getUsername().equals(username)) {
                u.setLoggedIn(false);
                userRepository.save(u);
                return "User logged out";
            }
        }

        return "FAILURE";
    }
    @CrossOrigin
    @DeleteMapping("/users/all")
    public Status deleteUsers(){
        userRepository.deleteAll();
        return Status.SUCCESS;
    }


}
