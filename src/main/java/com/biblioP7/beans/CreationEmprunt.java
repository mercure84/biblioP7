package com.biblioP7.beans;

/**
 * classe destinée à être utilisé pour la création d'un emprunt et utiliser EmpruntController avec un objet JSON
 */
public class CreationEmprunt {

    private String membreId;
    private String livreId;

    public String getMembreId() {
        return membreId;
    }

    public void setMembreId(String membreId) {
        this.membreId = membreId;
    }

    public String getLivreId() {
        return livreId;
    }

    public void setLivredId(String livreId) {
        this.livreId = livreId;
    }

    public CreationEmprunt() {
    }
}