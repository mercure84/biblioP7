package com.biblioP7.controllers;


import com.biblioP7.beans.Emprunt;
import com.biblioP7.dao.EmpruntDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmpruntController {

    @Autowired
    private EmpruntDao empruntDao;

    @RequestMapping(value="/listeEmprunts", method= RequestMethod.GET)
    public List<Emprunt> listeEmprunts(){
        List<Emprunt> emprunts = empruntDao.findAll();
        return emprunts;
    }


}
