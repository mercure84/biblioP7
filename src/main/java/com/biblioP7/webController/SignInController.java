package com.biblioP7.webController;


import com.biblioP7.beans.LoginForm;
import com.biblioP7.feignClient.MembreServiceClient;

import com.biblioP7.security.JwtRequest;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.sql.SQLOutput;


@Controller
@SessionAttributes("tokenJWT")
public class SignInController {


    @Autowired
    MembreServiceClient membreServiceClient;


    @GetMapping("/client/signIn")
    public String signInPage(Model model, String connexion) {
        model.addAttribute("login", new LoginForm());
        try {
            if (connexion.equals("erreur")) {
                model.addAttribute("connexion", "nOK");
            }
        } catch (Exception e) {

        }
        return "signIn";
    }


    @PostMapping("/client/signIn")
    public String seConnecter(@ModelAttribute LoginForm login, Model model) {

        try {
            JwtRequest jwtRequest = new JwtRequest();
            jwtRequest.setPassword(login.getPassword());
            jwtRequest.setUsername(login.getEmail());

            ResponseEntity<?> reponseAuth = membreServiceClient.login(jwtRequest);
            if (reponseAuth.getStatusCode().value() == 200) {
                String token = reponseAuth.getBody().toString();
                String tokenJWT = "Bearer " + token.substring(7,token.length()-1);
                System.out.println("Token officiel = " + tokenJWT);
                model.addAttribute("tokenJWT", tokenJWT);
                model.addAttribute("connexion", "OK");
                return "index";

            } else {
                return "redirect:/client/signIn?connexion=erreur";
            }

        } catch (Exception e) {
            return "redirect:/client/signIn?connexion=erreur";

        }


    }


}
