package com.lpdm.msauthentication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lpdm.msauthentication.model.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="app_role")
public class AppRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String roleName;

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name = "app_role_app_user",
            joinColumns = @JoinColumn(name = "app_role_id"),
            inverseJoinColumns = @JoinColumn(name = "app_user_id"))
    private List<AppUser> users;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @JsonIgnore
    public List<AppUser> getUsers() {
        return users;
    }

    public void setUsers(List<AppUser> users) {
            this.users = users;
    }


}


