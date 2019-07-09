package com.biblioP7.utils;

import com.biblioP7.beans.Emprunt;
import com.biblioP7.beans.Livre;
import com.biblioP7.beans.Membre;
import com.biblioP7.dao.EmpruntDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class batchRelance {

    @Autowired
    private static EmpruntDao empruntDao;


    public static void main(String[] args) {

        List<Emprunt> empruntsEchusEncours = empruntDao.findEmpruntsExpires(false, new Date());

        List<String> messages = new ArrayList<String>();

    if (empruntsEchusEncours.size() == 0){
        messages.add("AUCUN MEMBRE A RELANCER :)");
    } else {
        for(int i = 0; i<empruntsEchusEncours.size(); i++){

            messages.add(genererMail(empruntsEchusEncours.get(i)));

        }
    }

        System.out.println("Emprunts échus : \n" + empruntsEchusEncours);
        System.out.println("Messages générés : \n" + messages);

    }

    private static String genererMail(Emprunt emprunt) {
        Membre membre = emprunt.getMembre();
        Livre livre = emprunt.getLivre();
        String texte = "SEND TO :" + membre.getEmail() + "\n"
                +"Bonjour " + membre.getPrenom() + membre.getNom() + ", \n "+
                "Votre bibliothèque attend le retour du livre : " + livre.getTitre() + ", que vous avez emprunté en date du " + emprunt.getDebutDate() + ". \n" +
                "Ce livre aurait du être restitué avant le " + emprunt.getFinDate() + ". \n" +
                "Nous vous prions de nous le rapporter dans les meilleurs délais pour que d'autres lecteurs puissent emprunter cet ouvrage. \n" +
                "En vous remerciant pour votre compréhension. \n" + "Votre bibliothécaire préféré.";


        return texte;
    }


}
