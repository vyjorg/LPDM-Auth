package com.lpdm.msauthentication.dao;

import com.lpdm.msauthentication.beans.usertypes.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
}
