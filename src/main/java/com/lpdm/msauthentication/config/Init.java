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

@Component
public class Init implements CommandLineRunner {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private AppRoleRepository appRoleRepository;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void run(String... args) throws Exception {
        AppUser admin1 = new AppUser("vianney.diris@gmail.com", "admin1");
        AppUser admin2 = new AppUser("rockntrek@gmail.com", "admin2");
        AppUser admin3 = new AppUser("juliencauwet@yahoo.fr", "admin3");

        AppUser regUser1 = new AppUser("jaycecordon@gmail.com", "reguser1");

        AppRole admin = new AppRole("ADMIN");
        AppRole prod = new AppRole("PRODUCTOR");
        AppRole cons = new AppRole("CONSUMER");
        AppRole deliverer = new AppRole("DELIVERER");

        //admin1.getRoles().add(admin);
        //admin2.getRoles().add(cons);

        logger.info("entrée des rôles");
        appRoleRepository.save(admin);
        appRoleRepository.save(prod);
        appRoleRepository.save(cons);
        appRoleRepository.save(deliverer);

        logger.info("entrée des users");
        appUserRepository.save(admin1);
        appUserRepository.save(admin2);
        appUserRepository.save(admin3);

        appUserRepository.save(regUser1);





    }
}
