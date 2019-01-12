package com.lpdm.msauthentication.beans;

import com.lpdm.msauthentication.beans.enumeration.AccessEnum;
import com.lpdm.msauthentication.beans.usertypes.AppRole;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "app_user")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name= "password")
    private String password;

    @Transient
    private Enum<AccessEnum> role;

    //@ManyToMany
    //private Set<AppRole> roles = new HashSet<>();

    @Column(name = "name")
    private String name;

    @Column(name= "first_name")
    private String firstName;

    //private List<ProductBean> products;

    public AppUser() {
    }

    public AppUser(@NotNull String email, @NotNull String password) {
        this.email = email;
        this.password = password;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   // public Enum<AccessEnum> getRole() {
   //     return role;
   // }
//
   // public void setRole(Enum<AccessEnum> role) {
   //     this.role = role;
   // }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    //public Set<AppRole> getRoles() {
    //    return roles;
    //}

    //public void setRoles(Set<AppRole> roles) {
    //    this.roles = roles;
    //}

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", name='" + name + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }

    public void login(){

    }

    public void logout(){

    }
}
