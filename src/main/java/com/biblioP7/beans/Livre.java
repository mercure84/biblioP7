package com.biblioP7.beans;

import javax.persistence.*;

@Entity
@Table(name="livre")
public class Livre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="isbn")
    private String isbn;

    @Column(name="titre")
    private String title;

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


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuteurNom() {
        return auteurNom;
    }

    public void setAuteurNom(String auteurNom) {
        this.auteurNom = auteurNom;
    }

    public String getAuteurPrenom() {
        return auteurPrenom;
    }

    public void setAuteurPrenom(String auteurPrenom) {
        this.auteurPrenom = auteurPrenom;
    }

    public String getEditeur() {
        return editeur;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }

    public String collection() {
        return collection;
    }

    public void collection(String collection) {
        this.collection = collection;
    }

    public String getEtiquette() {
        return etiquette;
    }

    public void setEtiquette(String etiquette) {
        this.etiquette = etiquette;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Livre() {
    }

    @Override
    public String toString() {
        return "Livre{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", auteurNom='" + auteurNom + '\'' +
                ", auteurPrenom='" + auteurPrenom + '\'' +
                ", editeur='" + editeur + '\'' +
                ", collection='" + collection + '\'' +
                ", etiquette='" + etiquette + '\'' +
                ", disponible=" + disponible +
                '}';
    }
}
