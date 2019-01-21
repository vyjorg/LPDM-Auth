package com.lpdm.msauthentication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "app_user")
public class AppUser {

    public AppUser(@NotNull String email, @NotNull String password) {
        this.email = email;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name= "password")
    private String password;

    @Column(name = "roles")
    @OneToMany
    @JoinColumn(name = "app_user_id")
    private Set<UserRoles> roles = new HashSet<>();

    @Column(name = "name")
    private String name;

    @Column(name= "first_name")
    private String firstName;

    @Column(name = "tel")
    private String tel;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "registration_date")
    private LocalDateTime registrationDate;

    @Column(name = "address_id")
    private int addressId;

    @Column(name = "active")
    private Boolean active = true;

}
