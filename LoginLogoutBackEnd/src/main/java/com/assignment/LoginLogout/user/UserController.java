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
    public Status registerUser(@Valid @RequestBody User newUser){
        List<User> users = userRepository.findAll();
        if (users.contains(newUser)) {
            System.out.println("User already exists!");
            return Status.USER_ALREADY_EXISTS;
        }
        userRepository.save(newUser);
        return Status.SUCCESS;
    }
    @CrossOrigin
    @PostMapping("/users/login")
    public Status loginUser(@Valid @RequestBody User user){
        List<User> users = userRepository.findAll();
        for (User u : users) {
            if (u.equals(user)) {
                user.setLoggedIn(true);
                userRepository.save(user);
                return Status.SUCCESS;
            }
        }
        return Status.FAILURE;
    }
    @CrossOrigin
    @PostMapping("/users/logout")
    public Status logoutUser(@Valid @RequestBody User user) {
        List<User> users = userRepository.findAll();

        for (User u : users) {
            if (u.equals(user)) {
                user.setLoggedIn(false);
                userRepository.save(user);
                return Status.SUCCESS;
            }
        }

        return Status.FAILURE;
    }
    @CrossOrigin
    @DeleteMapping("/users/all")
    public Status deleteUsers(){
        userRepository.deleteAll();
        return Status.SUCCESS;
    }


}
