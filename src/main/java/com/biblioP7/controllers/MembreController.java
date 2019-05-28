package com.biblioP7.controllers;

import com.biblioP7.beans.Membre;
import com.biblioP7.dao.MembreDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class MembreController {

    @Autowired
    private MembreDao membreDao;


    @RequestMapping(value="/listeMembres", method= RequestMethod.GET)
    public List<Membre> listeMembres(){
        List<Membre> membres = membreDao.findAll();
        return membres;
    }

    @GetMapping(value="/Membre/{id}")
    public Membre detailMembre(@PathVariable int id){
        return membreDao.findById(id);
    }

    @PostMapping(value="/ajouterMembre")
    public void ajouterMembre(@RequestBody Membre membre){
        membreDao.save(membre);
    }



}
