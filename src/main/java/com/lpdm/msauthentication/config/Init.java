package com.lpdm.msauthentication.config;

import com.lpdm.msauthentication.beans.AppUser;
import com.lpdm.msauthentication.beans.enumeration.AccessEnum;
import com.lpdm.msauthentication.dao.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Init implements CommandLineRunner {

    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public void run(String... args) throws Exception {

        AppUser admin1 = new AppUser("vianney.diris@gmail.com", "admin1", AccessEnum.ADMIN);
        AppUser admin2 = new AppUser("rockntrek@gmail.com", "admin2", AccessEnum.ADMIN);
        AppUser admin3 = new AppUser("juliencauwet@yahoo.fr", "admin3", AccessEnum.ADMIN);

        AppUser regUser1 = new AppUser("jaycecordon@gmail.com", "reguser1", AccessEnum.CONSUMER);

        appUserRepository.save(admin1);
        appUserRepository.save(admin2);
        appUserRepository.save(admin3);

        appUserRepository.save(regUser1);


    }
}
