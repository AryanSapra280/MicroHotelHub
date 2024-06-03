package com.userservice.UserService.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userservice.UserService.entities.User;
import com.userservice.UserService.exceptions.customException.UserNotFound;
import com.userservice.UserService.services.UserService;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        List<User> users = userService.getAllUser();
        return new ResponseEntity<List<User>>(users,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        try {
            User user = userService.getUserById(id);
            return new ResponseEntity<User>(user,HttpStatus.OK);
        }catch(UserNotFound ex) {
            return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping("/save")
    public ResponseEntity<?> postMethodName(@RequestBody User user) {
        try {
            String email = user.getEmail();
            user.setEmail(email.toLowerCase());
            User savedUser = userService.saveUser(user);
            return new ResponseEntity<User>(savedUser,HttpStatus.CREATED);    
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
        
    }
    
    @PutMapping("update/{email}")
    public ResponseEntity<?> putMethodName(@PathVariable String email, @RequestBody User user) {
        User updatedUser = null;
        user.setEmail(email);
        try {
            updatedUser = userService.updateUser(user);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }
    
}
