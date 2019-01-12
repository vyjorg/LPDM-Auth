package com.lpdm.msauthentication.config;

import com.lpdm.msauthentication.beans.AppUser;
import com.lpdm.msauthentication.beans.usertypes.AppRole;
import com.lpdm.msauthentication.dao.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Init implements CommandLineRunner {

    @Autowired
    private AppUserRepository appUserRepository;

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

        appUserRepository.save(admin1);
        appUserRepository.save(admin2);
        appUserRepository.save(admin3);

        appUserRepository.save(regUser1);


    }
}
