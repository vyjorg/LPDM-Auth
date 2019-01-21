package com.lpdm.msauthentication.config;

import com.lpdm.msauthentication.dao.AppRoleRepository;
import com.lpdm.msauthentication.dao.AppUserRepository;
import com.lpdm.msauthentication.model.AppRole;
import com.lpdm.msauthentication.model.AppUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Init implements CommandLineRunner {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private AppRoleRepository appRoleRepository;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void run(String... args) throws Exception {

        AppUser a1 = appUserRepository.getAppUserById(1);
        AppUser a2 = appUserRepository.getAppUserById(2);
        AppUser a3 = appUserRepository.getAppUserById(3);



    }
}
