package com.lpdm.msauthentication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private AppRole appRole;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private AppUser appUser;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
