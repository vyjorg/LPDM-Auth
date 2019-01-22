package com.lpdm.msauthentication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lpdm.msauthentication.model.AppUser;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String roleName;

    @OneToMany
    @JoinColumn(name = "app_role_id")
    @JsonIgnore
    private Set<UserRoles> roles = new HashSet<>();



}
