package com.lpdm.msauthentication.beans.usertypes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppRole {

    @Id
    private Long id;
    @GeneratedValue
    private String roleName;
}
