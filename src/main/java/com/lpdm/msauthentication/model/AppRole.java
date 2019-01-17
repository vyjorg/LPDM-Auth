package com.lpdm.msauthentication.model;

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

    @ManyToOne(cascade = CascadeType.ALL)
    private AppUser appUser;

    /*
    @ManyToOne(cascade = CascadeType.ALL)
    private Area area;

    @OneToMany
    @JoinColumn(name = "route_id")
    private Set<Length> lengths =new HashSet<>();
     */
}
