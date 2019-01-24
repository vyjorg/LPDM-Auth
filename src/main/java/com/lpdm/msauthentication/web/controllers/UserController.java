package com.lpdm.msauthentication.web.controllers;

import com.lpdm.msauthentication.dao.AppRoleRepository;
import com.lpdm.msauthentication.dao.AppUserRepository;
import com.lpdm.msauthentication.model.AppRole;
import com.lpdm.msauthentication.web.exceptions.CannotCreateUserException;
import com.lpdm.msauthentication.web.exceptions.UserNotFoundException;
import com.lpdm.msauthentication.model.AppUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private AppRoleRepository appRoleRepository;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<AppUser> getAllUsers(){
        return appUserRepository.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AppUser getUserById(@PathVariable int id){

        AppUser appUser = appUserRepository.getAppUserById(id);
        if(appUser == null)
            throw new UserNotFoundException("Could not find any user matching this id " + id);

        return appUser;
    }

    @GetMapping(value = "/email/{email}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AppUser getUserByEmail(@PathVariable String email){
        AppUser appUser = appUserRepository.findByEmail(email);
        return appUser;
    }

    @GetMapping(value = "/username/{username}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    List<AppUser> getUserByUsername(@PathVariable String username){
        logger.info("user: " + username);

        List<AppUser> appUser = appUserRepository.findByNameLike(username);
        if(appUser == null)
            throw new UserNotFoundException("Could not find any user matching this username " + username);

        return appUser;
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AppUser addUser(@RequestBody AppUser user){

        AppUser appUser = appUserRepository.findByEmail(user.getEmail());

        if (appUser != null)
            throw new CannotCreateUserException("The user " + user.getEmail() + " already exists");

        return appUserRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id){
        appUserRepository.deleteById(id);
    }

    @GetMapping(value = "/roles/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<AppRole> getRolesByUser(@PathVariable("id") int id){
        AppUser appUser = appUserRepository.getAppUserById(id);
        List<AppRole> appRoles = appRoleRepository.getAppRolesByUsersEquals(appUser);
        return appRoles;
    }


    
}
