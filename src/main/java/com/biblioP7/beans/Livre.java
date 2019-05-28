package com.biblioP7.beans;

import javax.persistence.*;

@Entity
@Table(name="book")
public class Livre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="titre")
    private String title;

    @Column(name="auteur")
    private String auteur;

    @Column(name="anneePub")
    private int anneePub;




    public Livre() {
    }
}
