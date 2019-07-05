package com.biblioP7.controllers;


import com.biblioP7.beans.Emprunt;
import com.biblioP7.beans.Livre;
import com.biblioP7.beans.Membre;
import com.biblioP7.dao.EmpruntDao;
import com.biblioP7.dao.LivreDao;
import com.biblioP7.dao.MembreDao;
import com.biblioP7.beans.CreationEmprunt;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value="/EmpruntsMembre/{membreId}")
    public List<Emprunt> empruntsParMembre(@PathVariable int membreId){
        Membre membre = membreDao.findById(membreId);

        List<Emprunt> listeEmprunt = empruntDao.findEmpruntsByMembre(membre);

        return listeEmprunt;
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

        if(empruntAProlonger.isProlonge()){
            return null;

        } else {
            empruntAProlonger.setProlonge(true);
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

    @CrossOrigin("*")
    @RequestMapping(value="/stopperEmprunt/{id}")
    public Livre livreRendu(@PathVariable int id){

        //on tope l'emprunt à "rendu true" + modif de la date de fin
        Emprunt emprunt = empruntDao.findById(id);
        emprunt.setFinDate(new Date());
        emprunt.setRendu(true);

        //on tope le livre à disponible = true
        Livre livreRendu = livreDao.findById(emprunt.getLivre().getId());
        livreRendu.setDisponible(true);

        //on save les 2 entités livre / emprunt
        livreDao.save(livreRendu);
        empruntDao.save(emprunt);

        return livreRendu;


    }


    @CrossOrigin("*")
    @RequestMapping(value="/listeEmpruntsExpires", method= RequestMethod.GET)
    public List<Emprunt> empruntsExpires (){
        Date today = new Date();

        List<Emprunt> emprunts = empruntDao.findEmpruntsExpires(false, today);
        return emprunts;
    }


}
