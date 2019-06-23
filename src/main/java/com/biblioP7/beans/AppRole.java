package com.biblioP7.beans;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data@Entity
@NoArgsConstructor@AllArgsConstructor
public class AppRole {

    @Id
    private int id;

    @Column(name="role")
    private String roleName;

}
