package com.lpdm.msauthentication.web.controllers;

import com.lpdm.msauthentication.model.AppRole;
import com.lpdm.msauthentication.dao.AppRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    AppRoleRepository appRoleRepository;

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
        return appRoleRepository.findById(id);
    }

    @GetMapping("/per_user/{id}")
    public  List<AppRole> getRolesPerUserId(@PathVariable("id") int userId){return null;}

    @GetMapping("/{user_id}/{role_id}")
    public  void addRole(@PathVariable ("user_id") int userId, @PathVariable ("role_id") int roleId){

    }
}
