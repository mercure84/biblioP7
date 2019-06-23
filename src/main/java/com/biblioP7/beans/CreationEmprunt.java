package com.biblioP7.beans;

import lombok.Data;

/**
 * classe destinée à être utilisé pour la création d'un emprunt et utiliser EmpruntController avec un objet JSON
 */

@Data
public class CreationEmprunt {

    private String membreId;
    private String livreId;


}