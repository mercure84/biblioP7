package com.biblioP7.controllers;

import com.biblioP7.beans.Membre;
import com.biblioP7.dao.MembreDao;
import com.biblioP7.security.EncryptedPasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
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

    @CrossOrigin("*")
    @PostMapping(value="/ajouterMembre")
    public void ajouterMembre(@RequestBody Membre membre){

        String encodedPassword = EncryptedPasswordUtils.encryptePassword(membre.getEncryptedPassword());
        membre.setEncryptedPassword(encodedPassword);
        membreDao.save(membre);
    }



}
