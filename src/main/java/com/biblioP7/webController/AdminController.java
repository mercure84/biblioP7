package com.biblioP7.webController;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController {


    @GetMapping("/client/admin")
    public String afficherPageAdmin(HttpSession session, Model model){





        return "admin";
    }



}
