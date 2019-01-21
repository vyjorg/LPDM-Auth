package com.lpdm.msauthentication.dao;

import com.lpdm.msauthentication.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser,Integer> {

    AppUser getAppUserById(int id);

    AppUser findByEmail(String email);

    List<AppUser> findByName(String name);


}
