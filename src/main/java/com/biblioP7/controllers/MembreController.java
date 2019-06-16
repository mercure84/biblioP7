package com.biblioP7.controllers;

import com.biblioP7.beans.Membre;
import com.biblioP7.dao.MembreDao;
import com.biblioP7.security.EncryptedPasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.List;


@RestController
public class MembreController {

    @Autowired
    private MembreDao membreDao;

    @CrossOrigin("*")
    @RequestMapping(value="/listeMembres", method= RequestMethod.GET)
    public List<Membre> listeMembres(){
        List<Membre> membres = membreDao.findAll();
        return membres;
    }
    @CrossOrigin("*")
    @GetMapping(value="/Membre/{id}")
    public Membre detailMembre(@PathVariable int id){
        return membreDao.findById(id);
    }

    @CrossOrigin("*")
    @PostMapping(value="/ajouterMembre")
    public ResponseEntity<Membre> ajouterMembre(@RequestBody Membre membre){

        String encodedPassword = EncryptedPasswordUtils.encryptePassword(membre.getEncryptedPassword());
        membre.setEncryptedPassword(encodedPassword);
        Membre result = membreDao.save(membre);
        try {
            return ResponseEntity.created(new URI("/Membre/" + result.getId()))
                    .body(result);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }

    }

    @CrossOrigin("*")
    @PostMapping(value="/seConnecter")
    public String connection(){


        return "/";
    }




}
