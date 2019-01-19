package com.lpdm.msauthentication.web.controllers;

import com.lpdm.msauthentication.dao.AppUserRepository;
import com.lpdm.msauthentication.web.exceptions.CannotCreateUserException;
import com.lpdm.msauthentication.web.exceptions.UserNotFoundException;
import com.lpdm.msauthentication.model.AppUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AppUserRepository appUserRepository;

    @GetMapping("/")
    public List<AppUser> getAllUsers(){
        return appUserRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<AppUser> getUserById(@PathVariable int id){

        Optional <AppUser> appUser = appUserRepository.findById(id);
        if(!appUser.isPresent())
            throw new UserNotFoundException("Could not find any user matching this id " + id);

        return appUser;
    }

    @GetMapping("/email/{email}")
    public Optional<AppUser> getUserByEmail(@PathVariable String email){
        Optional<AppUser> appUser = appUserRepository.findByEmail(email);
        if(!appUser.isPresent())
            throw new UserNotFoundException("Could not find any user matching this email " + email);
        return appUser;
    }

    @GetMapping("/username/{username}")
    List<AppUser> getUserByUsername(@PathVariable String username){
        logger.info("user: " + username);

        List<AppUser> appUser = appUserRepository.findByName(username);
        if(appUser == null)
            throw new UserNotFoundException("Could not find any user matching this username " + username);

        return appUser;
    }

    @PostMapping("/")
    public AppUser addUser(@RequestBody AppUser user){

        Optional<AppUser> appUser = appUserRepository.findByEmail(user.getEmail());

        if (appUser == null)
            throw new CannotCreateUserException("The user " + user.getEmail() + " already exists");

        return appUserRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id){
        appUserRepository.deleteById(id);
    }
}
