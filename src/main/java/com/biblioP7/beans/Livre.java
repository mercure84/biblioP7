package com.biblioP7.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@Table(name="livre")
@AllArgsConstructor @NoArgsConstructor
public class Livre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="isbn")
    private String isbn;

    @Column(name="titre")
    private String titre;

    @Column(name="auteur_nom")
    private String auteurNom;

    @Column(name="auteur_Prenom")
    private String auteurPrenom;

    @Column(name="editeur")
    private String editeur;

    @Column(name="collection")
    private String collection;

    @Column(name="etiquette")
    private String etiquette;

    @Column(name="disponible")
    private boolean disponible;


}
