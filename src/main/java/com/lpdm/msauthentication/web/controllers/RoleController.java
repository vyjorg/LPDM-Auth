package com.lpdm.msauthentication.web.controllers;

import com.lpdm.msauthentication.dao.AppUserRepository;
import com.lpdm.msauthentication.model.AppRole;
import com.lpdm.msauthentication.dao.AppRoleRepository;
import com.lpdm.msauthentication.model.AppUser;
import com.lpdm.msauthentication.web.exceptions.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    AppRoleRepository appRoleRepository;

    @Autowired
    AppUserRepository appUserRepository;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AppRole addRole(@RequestBody AppRole role){
        return appRoleRepository.save(role);
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<AppRole> getAllRoles(){
        return appRoleRepository.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AppRole getRoleById(@PathVariable("id") int id){
        logger.info("rehcerche de l'appRole n° " + id);
        return appRoleRepository.getAppRoleById(id);
    }

    /**
     * list of roles of the user matching the id
     * @param userId
     * @return the list of roles per userId
     */
    @GetMapping(value = "/per_user/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public  List<AppRole> getRolesPerUserId(@PathVariable("id") int userId){
       AppUser user = appUserRepository.getAppUserById(userId);
       List<AppRole> appRoles = appRoleRepository.getAppRolesByUsersEquals(user);
       return appRoles;
    }

    /**
     * add the role matching roleId to the user matching userId
     * @param userId
     * @param roleId
     * @return the role saved
     */
    @GetMapping(value = "/{user_id}/{role_id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AppRole addRoleperUserId(@PathVariable ("user_id") int userId, @PathVariable ("role_id") int roleId) {
        AppRole appRole = appRoleRepository.getAppRoleById(roleId);
        AppUser appUser = appUserRepository.getAppUserById(userId);
        appRole.getUsers().add(appUser);
        return appRoleRepository.save(appRole);
    }

    /**
     * check if the user matching the id has the role entered as parameter
     * @param userId
     * @param roleId
     * @return user if it matches the role or null if it does not
     */
    @GetMapping(value = "/check/{user_id}/{role_id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AppUser returnUserIfHasRole(@PathVariable("user_id") int userId, @PathVariable("role_id") int roleId) {
        AppUser appUser = appUserRepository.getAppUserById(userId);
        if (appUser == null)
            throw new UserNotFoundException("L'utilisateur n'existe pas");
        List<AppRole> appRoles = appRoleRepository.getAppRolesByUsersEquals(appUser);

        for (AppRole role : appRoles){
            if (role.equals(appRoleRepository.getAppRoleById(roleId)))
                return appUser;
        }
        return null;
    }



}
