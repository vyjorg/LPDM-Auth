package com.lpdm.msauthentication.web.controllers;

import com.lpdm.msauthentication.dao.AppRoleRepository;
import com.lpdm.msauthentication.dao.AppUserRepository;
import com.lpdm.msauthentication.model.AppRole;
import com.lpdm.msauthentication.model.mslocation.Address;
import com.lpdm.msauthentication.proxies.MsLocationProxy;
import com.lpdm.msauthentication.web.exceptions.CannotCreateUserException;
import com.lpdm.msauthentication.web.exceptions.IncorrectPasswordException;
import com.lpdm.msauthentication.web.exceptions.UserNotFoundException;
import com.lpdm.msauthentication.model.AppUser;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCrypt;
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

    @Autowired
    private MsLocationProxy locationProxy;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<AppUser> getAllUsers(){
        return appUserRepository.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AppUser getUserById(@PathVariable int id){

        AppUser appUser = appUserRepository.findById(id);
        if(appUser == null)
            throw new UserNotFoundException("Could not find any user matching this id " + id);

        assignAddressToUSer(appUser);

        return appUser;
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AppUser login(@RequestBody AppUser user){
        AppUser DbUser = appUserRepository.findByEmail(user.getEmail());


        if (DbUser == null){
            logger.info("Pas d'utilisateur trouvé");
            throw new UserNotFoundException("Could not find any user matching this email " + user.getEmail());

        } else if(BCrypt.checkpw(user.getPassword(), DbUser.getPassword())){
            assignAddressToUSer(user);
            return DbUser;
        } else {
            logger.info("Mot de passe incorrect: " + user.getPassword() + " " + DbUser.getPassword());
            throw new UserNotFoundException("Incorrect Password");
        }
    }

    @GetMapping(value = "/email/{email}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AppUser getUserByEmail(@PathVariable String email){
        logger.info("step 1");
        AppUser appUser= null;
        try {
            logger.info("step 2");
            appUser = appUserRepository.findByEmail(email);
        }catch (NullPointerException e){
            logger.info("User not found");
            return null;
        }
        if(appUser != null) {
            logger.info(appUser.toString());
            assignAddressToUSer(appUser);
        }

        return appUser;
    }

    @GetMapping(value = "/username/{username}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    List<AppUser> getUserByUsername(@PathVariable String username){
        logger.info("user: " + username);

        List<AppUser> appUser = appUserRepository.findAllByNameContainingIgnoreCase(username);
        if(appUser.size() == 0)
            throw new UserNotFoundException("Could not find any user matching this username " + username);

        for (AppUser user : appUser)
            assignAddressToUSer(user);


        return appUser;
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AppUser addUser(@RequestBody AppUser user){

        AppUser appUser = appUserRepository.findByEmail(user.getEmail());

        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));

        if (appUser != null)
            throw new CannotCreateUserException("The user " + user.getEmail() + " already exists");
        if(user.getAddressId() < 1)
            assignAddressToUSer(appUser);

        return appUserRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id){
        appUserRepository.deleteById(id);
    }

    @GetMapping(value = "/per_role/{id}")
    public List<AppUser>getUsersByRole(@PathVariable("id") int id){
        AppRole role = appRoleRepository.getAppRoleById(id);
        List <AppUser> users = appUserRepository.getAppUsersByAppRoleEquals(role);

        if(users.size() > 0) {
            for (AppUser user : users)
                assignAddressToUSer(user);
        }

        return appUserRepository.getAppUsersByAppRoleEquals(role);
    }

    @PutMapping(value = "/updateuser", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AppUser updateAppUser(@RequestBody AppUser user){

        AppUser dbUser = appUserRepository.findById(user.getId());

        if (dbUser == null)
            throw  new UserNotFoundException("The user id: " + user.getId() + " does not exist");

        user.setPassword(dbUser.getPassword());
        assignAddressToUSer(user);
        logger.info("Modification de l'utilisateur");
        return appUserRepository.save(user);
    }


    //TODO : can't find oldpassword
    @PutMapping(value = "/password/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Boolean updatePassword(@PathVariable("id") int id, @RequestBody String oldPassword, @RequestBody String newPassword){
        AppUser dbUser = appUserRepository.getAppUserById(id);
        if(oldPassword == dbUser.getPassword()) {
            logger.info("user modification successful!");
            dbUser.setPassword(newPassword);
            return true;
        }else{
            logger.info("Old password incorrect, can't save the modifications");
            throw new IncorrectPasswordException("Incorrect password");
        }
    }


    private void assignAddressToUSer(AppUser user){
        Address address = null;

        try {
            address = locationProxy.findAddressById(user.getAddressId());
        }catch (FeignException e){
            logger.info("L'id ne correspond à aucune adresse");
        }

        logger.info("address: " + address);

        user.setAddress(address);
    }

    
}
