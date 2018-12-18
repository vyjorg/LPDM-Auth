package com.lpdm.msauthentication.web.controllers;

import com.lpdm.msauthentication.dao.AppUserRepository;
import com.lpdm.msauthentication.web.exceptions.UserNotFoundException;
import com.lpdm.msauthentication.beans.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

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

    @GetMapping("/{username}")
    public Optional <AppUser> getUserByUsername(@PathVariable String username){
        Optional <AppUser> appUser = appUserRepository.findByEmail(username);
        if(!appUser.isPresent())
            throw new UserNotFoundException("Could not find any user matching this id " + username);

        return appUser;
    }

    @PostMapping("/")
    public AppUser addUser(@RequestBody AppUser user){
        return appUserRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id){
        appUserRepository.deleteById(id);
    }
}
