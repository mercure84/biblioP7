package com.biblioP7.webController;


import com.biblioP7.feignClient.MembreServiceClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class SignInController {


    @Autowired
    MembreServiceClient membreServiceClient;


    @RequestMapping("/signIn")
    public String signInPage (Model model){
        return "signIn";
    }
//
//
//    @PostMapping("/signIn")
//    public void seConnecter(@ModelAttribute ){
//
//
//
//
//    }



}
