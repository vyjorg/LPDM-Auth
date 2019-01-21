package com.lpdm.msauthentication.dao;

import com.lpdm.msauthentication.model.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppRoleRepository extends JpaRepository<AppRole, Integer> {

    AppRole getAppRoleById(int id);


}
