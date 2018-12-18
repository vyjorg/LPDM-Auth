package com.lpdm.msauthentication.web.controllers;

import com.lpdm.msauthentication.beans.usertypes.AppRole;
import com.lpdm.msauthentication.dao.AppRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    AppRoleRepository appRoleRepository;

    @PostMapping("/")
    public AppRole addRole(@RequestBody AppRole role){
        return appRoleRepository.save(role);
    }
}
