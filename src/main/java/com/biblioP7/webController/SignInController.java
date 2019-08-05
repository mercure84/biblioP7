package com.biblioP7.webController;

import com.biblioP7.beans.Livre;
import com.biblioP7.restControllers.LivreController;
import com.biblioP7.security.JwtRequest;
import com.biblioP7.webController.tools.LoginBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class SignInController {


    @RequestMapping("/signIn")
    public String signInPage (Model model){
        model.addAttribute("loginBean", new LoginBean());
        return "signIn";
    }




    @PostMapping("/signIn")
    public void seConnecter(@ModelAttribute LoginBean loginBean){

        String uri = "http://localhost:8080/api/Livre/randomLivre";

        System.out.println("email = " + loginBean.getUsername() + ", pass = " + loginBean.getPassword() );


        RestTemplate restTemplate = new RestTemplate();

        Livre livreRandom = restTemplate.getForObject(uri, Livre.class);


        System.out.println(livreRandom.toString());



    }



}
