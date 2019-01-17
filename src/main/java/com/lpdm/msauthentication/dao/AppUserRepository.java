package com.lpdm.msauthentication.dao;

import com.lpdm.msauthentication.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser,Integer> {

    Optional<AppUser> findById(Integer integer);

    Optional<AppUser>findByEmail(String email);

    Optional<AppUser> findByName(String name);

}
