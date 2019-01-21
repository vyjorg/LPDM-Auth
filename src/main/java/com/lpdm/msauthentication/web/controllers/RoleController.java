package com.lpdm.msauthentication.web.controllers;

import com.lpdm.msauthentication.dao.AppUserRepository;
import com.lpdm.msauthentication.dao.UserRolesRepository;
import com.lpdm.msauthentication.model.AppRole;
import com.lpdm.msauthentication.dao.AppRoleRepository;
import com.lpdm.msauthentication.model.AppUser;
import com.lpdm.msauthentication.model.UserRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    AppRoleRepository appRoleRepository;

    @Autowired
    UserRolesRepository userRolesRepository;

    @Autowired
    AppUserRepository appUserRepository;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/")
    public AppRole addRole(@RequestBody AppRole role){
        return appRoleRepository.save(role);
    }

    @GetMapping("/")
    public List<AppRole> getAllRoles(){
        return appRoleRepository.findAll();
    }

    @GetMapping("/{id}")
    public AppRole getRoleById(@PathVariable("id") int id){
        logger.info("rehcerche de l'appRole n° " + id);
        return appRoleRepository.getAppRoleById(id);
    }

    @GetMapping("/per_user/{id}")
    public  List<AppRole> getRolesPerUserId(@PathVariable("id") int userId){
        List <AppRole> userRoles = new ArrayList<>();
        List <UserRoles> roles = userRolesRepository.getUserRolesByAppUserId(userId);
        logger.info("taille de 'roles':  " + roles.size());

        for (UserRoles role: roles) {
            logger.info("userId: " + userId + " userRoleId " + role.getAppRole().getId() + " userId: " + appRoleRepository.getAppRoleById(role.getId()));
            userRoles.add(appRoleRepository.getAppRoleById(role.getAppRole().getId()));
        }
        return userRoles;
    }


    @GetMapping("/{user_id}/{role_id}")
    public UserRoles addRoleperUserId(@PathVariable ("user_id") int userId, @PathVariable ("role_id") int roleId){
        UserRoles userRole = new UserRoles();
        userRole.setAppRole(appRoleRepository.getAppRoleById(roleId));
        userRole.setAppUser(appUserRepository.getAppUserById(userId));
        return userRolesRepository.save(userRole);
    }
}
