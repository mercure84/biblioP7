package com.biblioP7.webController;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import javax.servlet.http.HttpSession;


@Controller
public class MainController {

    @GetMapping(value="navbar")
    public String afficherNavbar(HttpSession session){
        return "navbar";
        }


    @GetMapping(value="/client")
    public String index (HttpSession session){
        return "index";
    }

    @GetMapping(value="/client/error")
    public String error(){


        return "error";

    }

    @GetMapping(value="/client/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "index";
    }



}





