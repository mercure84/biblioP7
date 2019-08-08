package com.biblioP7.webController;


import com.biblioP7.beans.LoginForm;
import com.biblioP7.feignClient.MembreServiceClient;

import com.biblioP7.security.JwtRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class SignInController {


    @Autowired
    MembreServiceClient membreServiceClient;


    @GetMapping("/client/signIn")
    public String signInPage (Model model){
        model.addAttribute("login", new LoginForm());
        return "signIn";
    }


    @PostMapping("/client/signIn")
    public String seConnecter(@ModelAttribute LoginForm login, Model model){

        try {

            JwtRequest jwtRequest = new JwtRequest();
            jwtRequest.setPassword(login.getPassword());
            jwtRequest.setUsername(login.getEmail());
            ResponseEntity<?> reponseAuth = membreServiceClient.login(jwtRequest);
            model.addAttribute("token" , reponseAuth );
            System.out.println(reponseAuth);
            return "index";

        } catch (Exception e){

            return "error";
        }







    }



}
