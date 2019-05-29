package com.biblioP7.controllers;


import com.biblioP7.beans.Livre;
import com.biblioP7.dao.LivreDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
public class LivreController {

    @Autowired
    private LivreDao livreDao;

    @CrossOrigin("*")
    @RequestMapping(value="/listeLivres", method= RequestMethod.GET)
    public List<Livre> listeLivres(){
        List<Livre> livres = livreDao.findAll();
        return livres;
    }

    @CrossOrigin("*")
    @GetMapping(value="/Livre/{id}")
    public Livre detailLivre(@PathVariable int id){
        return livreDao.findById(id);
    }

    @CrossOrigin("*")
    @GetMapping(value="/randomLivre")
    public Livre randomLivreDispo(){
        List<Livre> livresDispo = livreDao.findLivresByDisponibleIsTrue();
        Random rand = new Random();
        Livre livreRandom = livresDispo.get(rand.nextInt(livresDispo.size()));
        return livreRandom;
    }



    @PostMapping(value="/ajouterLivre")
    public void ajouterLivre(@RequestBody Livre livre){
        livreDao.save(livre);
    }

}