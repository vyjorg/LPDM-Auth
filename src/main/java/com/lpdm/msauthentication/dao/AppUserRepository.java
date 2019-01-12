package com.lpdm.msauthentication.dao;

import com.lpdm.msauthentication.beans.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser,Integer> {

    AppUser findByEmail(String email);

}
