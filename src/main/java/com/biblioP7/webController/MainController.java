package com.biblioP7.webController;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class MainController {

    @RequestMapping(value="navbar", method = RequestMethod.GET)
    public String afficherNavbar(){



        return "navbar";
        }


    @RequestMapping(value="/", method = RequestMethod.GET)
    public String index (){

        return "index";
    }

    @RequestMapping(value="/error", method = RequestMethod.GET)
    public String error(){


        return "error";

    }



}





