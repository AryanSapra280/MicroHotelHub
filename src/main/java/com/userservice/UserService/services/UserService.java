package com.userservice.UserService.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ctc.wstx.shaded.msv_core.util.Uri;
import com.userservice.UserService.dtos.Rating;
import com.userservice.UserService.entities.User;
import com.userservice.UserService.exceptions.customException.DuplicateEmail;
import com.userservice.UserService.exceptions.customException.UserNotFound;
import com.userservice.UserService.repositories.IUserRepositories;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepositories userRepositories;
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public List<User> getAllUser() {
        List<User>users =  userRepositories.findAll();
        for(int i=0;i<users.size();i++) {
            ArrayList<Rating> ratings = restTemplate.getForObject("http://localhost:8082/rating/user/"+users.get(i).getEmail(),ArrayList.class);
            users.get(i).setRatings(ratings);
        }
        return users;
    }

    @Override
    public User getUserByEmail(String email) {
        
        //one line solution
        User user = userRepositories.findByEmail(email).orElseThrow(()->new UserNotFound("User doesn't exist with this email id:" + email));
        // String ratingUrl = String.format("http://localhost:8082/rating/user/%s", user.getEmail()) ;
        ArrayList<Rating> ratings = restTemplate.getForObject("http://localhost:8082/rating/user/"+user.getEmail(),ArrayList.class);
        user.setRatings(ratings);
        return user;
        // User foundUser = null;
        // try {
        //     foundUser = userRepositories.findById(id).get();
        // }catch(NullPointerException e) {
        //     throw new UserNotFound("User doesn't exist with this id:" + id);
        // }
        // return foundUser;
    }

    @Override
    public User saveUser(User user) {
        String userId = UUID.randomUUID().toString();
        user.setUser_id(userId);
        User savedUser = null;
        try {
            savedUser = userRepositories.save(user);
        }catch(Exception exception) {
            throw new DuplicateEmail("This email is already registered");
        }
        return savedUser;
    }
    
    @Override
    public User updateUser(User user) {
        if(user.getEmail() == null) {
            throw new RuntimeException("Please provide a registered email");
        }
        User foundUser = userRepositories.findByEmail(user.getEmail()).
        orElseThrow(()->new UserNotFound("user doesn't exist with this email"));
        updateDetails(foundUser,user);
        return userRepositories.save(foundUser);
    }
    private void updateDetails(User foundUser, User user) {
        if(user.getAbout()!=null) {
            foundUser.setAbout(user.getAbout());
        }
        if(user.getName()!=null) {
            foundUser.setName(user.getName());
        }
    }

}
