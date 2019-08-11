package com.biblioP7.webController;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class MainController {

    @RequestMapping(value="navbar", method = RequestMethod.GET)
    public String afficherNavbar(){
        return "navbar";
        }


    @RequestMapping(value="/client", method = RequestMethod.GET)
    public String index (HttpSession session){
        return "index";
    }

    @RequestMapping(value="/client/error", method = RequestMethod.GET)
    public String error(){


        return "error";

    }

    @RequestMapping(value="/client/logout", method = RequestMethod.GET)
    public String logout(HttpSession session){
        session.invalidate();
        return "index";
    }



}





