package com.lpdm.msauthentication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lpdm.msauthentication.model.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppRole {

    public AppRole(String roleName){
        this.roleName = roleName;
    }

    @Id
    @GeneratedValue
    private int id;

    @GeneratedValue
    private String roleName;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private AppUser appUser;

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

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    /*
    @ManyToOne(cascade = CascadeType.ALL)
    private Area area;

    @OneToMany
    @JoinColumn(name = "route_id")
    private Set<Length> lengths =new HashSet<>();
     */
}
