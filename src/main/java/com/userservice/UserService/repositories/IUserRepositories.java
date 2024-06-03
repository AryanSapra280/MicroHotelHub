package com.userservice.UserService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.userservice.UserService.entities.User;
import java.util.Optional;


@Repository
public interface IUserRepositories extends JpaRepository<User,String>{
    
    Optional<User>findByEmail(String email);
}
