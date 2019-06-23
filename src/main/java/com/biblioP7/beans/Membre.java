package com.biblioP7.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Data
@Entity
@Table(name="membre")
@AllArgsConstructor @NoArgsConstructor
public class Membre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="nom")
    private String nom;

    @Column(name="prenom")
    private String prenom;

    @Column(name="email")
    private String email;

    @Column(name="encryptedpassword")
    private String encryptedPassword;

    @Column(name="dateinscription")
    private Date dateInscription;

    @Column(name="portable")
    private String portable;

    @ManyToMany(fetch=FetchType.EAGER)
    private Collection<AppRole> roles = new ArrayList<>();
}
