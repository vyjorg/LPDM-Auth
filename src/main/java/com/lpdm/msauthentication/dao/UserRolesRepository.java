package com.lpdm.msauthentication.dao;

import com.lpdm.msauthentication.model.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRoles, Integer> {

    List<UserRoles> getUserRolesByAppUserId(int appUserId);
}
