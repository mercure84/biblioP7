package com.biblioP7.controllers;


import com.biblioP7.beans.Emprunt;
import com.biblioP7.beans.Livre;
import com.biblioP7.beans.Membre;
import com.biblioP7.dao.EmpruntDao;
import com.biblioP7.dao.LivreDao;
import com.biblioP7.dao.MembreDao;
import com.biblioP7.utils.CreationEmprunt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
public class EmpruntController {

    @Autowired
    private EmpruntDao empruntDao;

    @Autowired
    private MembreDao membreDao;

    @Autowired
    private LivreDao livreDao ;



    @RequestMapping(value="/listeEmprunts", method= RequestMethod.GET)
    public List<Emprunt> listeEmprunts(){
        List<Emprunt> emprunts = empruntDao.findAll();
        return emprunts;
    }


    @PostMapping(value="/creerEmprunt")
    public Emprunt creerEmprunt(@RequestBody CreationEmprunt creationEmprunt){

        System.out.println("dataJSON = " + creationEmprunt);
        int membreId = Integer.parseInt(creationEmprunt.getMembreId());
        int livreId = Integer.parseInt(creationEmprunt.getLivreId());

        System.out.println("livreId = " + livreId + "membreId = " + membreId) ;

        Date dateDebut = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime((dateDebut));
        c.add(Calendar.DATE, 28);
        Date dateFin = c.getTime();
        System.out.println("date début = " + dateDebut +", date de fin = " + dateFin);

        Membre membre = membreDao.findById(membreId);
        Livre livre = livreDao.findById(livreId);

        if (!livre.isDisponible()){
            //le livre n'est pas disponible, on ne peut pas l'emprunter !
            return null;

        }

        else {

            Emprunt nouvelEmprunt = new Emprunt();

            nouvelEmprunt.setDebutDate(dateDebut);
            nouvelEmprunt.setFinDate(dateFin);
            nouvelEmprunt.setLivre(livre);
            nouvelEmprunt.setMembre(membre);

            empruntDao.save(nouvelEmprunt);

            //passage et sauvegarde du livre en tant que non disponible (à modifier plus tard si nous gérons la quantité ?)
            livre.setDisponible(false);
            livreDao.save(livre);
            return nouvelEmprunt;
        }

    }

    @CrossOrigin("*")
    @RequestMapping(value="/prolongerEmprunt/{id}", method= RequestMethod.GET)
    public Emprunt prolongerEmprunt(@PathVariable int id){

        Emprunt empruntAProlonger = empruntDao.findById(id);

        if(empruntAProlonger.isaEteProlonge()){
            return null;

        } else {
            empruntAProlonger.setaEteProlonge(true);
            //ajout de 28 jours à dateFin
            Date dateFin = empruntAProlonger.getFinDate();
            Calendar c = Calendar.getInstance();
            c.setTime(dateFin);
            c.add(Calendar.DATE, 28);
            Date dateFinBis = c.getTime();

            empruntAProlonger.setFinDate(dateFinBis);
            empruntDao.save(empruntAProlonger);
            return empruntAProlonger;

        }


    }


}
