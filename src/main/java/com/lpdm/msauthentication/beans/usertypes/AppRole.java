package com.lpdm.msauthentication.beans.usertypes;

import com.lpdm.msauthentication.beans.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppRole {

    public AppRole(String roleName){
        this.roleName = roleName;
    }

    @Id
    private int id;

    @GeneratedValue
    private String roleName;

    //@ManyToMany
    //private Set<AppUser> appUser = new HashSet<>();
}
