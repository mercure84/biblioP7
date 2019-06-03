package com.biblioP7.controllers;


import com.biblioP7.beans.Emprunt;
import com.biblioP7.beans.Livre;
import com.biblioP7.beans.Membre;
import com.biblioP7.dao.EmpruntDao;
import com.biblioP7.dao.LivreDao;
import com.biblioP7.dao.MembreDao;
import com.biblioP7.utils.DataJSON;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
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
    public ResponseEntity<Emprunt> creerEmprunt(@RequestBody DataJSON dataJSON){


        int membreId = Integer.parseInt(dataJSON.getParam1());
        int livreId = Integer.parseInt(dataJSON.getParam2());

        System.out.println("livreId = " + livreId + "membreId = " + membreId) ;

        Date dateDebut = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime((dateDebut));
        c.add(Calendar.DATE, 28);
        Date dateFin = c.getTime();
        System.out.println("date début = " + dateDebut +", date de fin = " + dateFin);

        Membre membre = membreDao.findById(membreId);
        Livre livre = livreDao.findById(livreId);

        Emprunt nouvelEmprunt = new Emprunt();

        nouvelEmprunt.setDebutDate(dateDebut);
        nouvelEmprunt.setFinDate(dateFin);
        nouvelEmprunt.setLivre(livre);
        nouvelEmprunt.setMembre(membre);

        empruntDao.save(nouvelEmprunt);
        return new ResponseEntity<Emprunt>(HttpStatus.NO_CONTENT);


    }


}
