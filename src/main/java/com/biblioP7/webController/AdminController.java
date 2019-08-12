package com.biblioP7.webController;


import com.biblioP7.beans.Emprunt;
import com.biblioP7.beans.Livre;
import com.biblioP7.feignClient.EmpruntServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);


    @Autowired
    EmpruntServiceClient empruntServiceClient;

    @GetMapping("/client/admin")
    public String afficherPageAdmin(HttpSession session, Model model){

        String token = session.getAttribute("token").toString();

        //on charge la liste des emprunts en cours
        List<Emprunt> empruntsEnCours = empruntServiceClient.listeEmpruntsEncours(token);
        model.addAttribute("empruntsEncours", empruntsEnCours);
        return "admin";
    }

    @GetMapping("/client/stopperEmprunt")
    public String stopperEmprunt(Model model, String empruntId, HttpSession session){

        String token = session.getAttribute("token").toString();
        try{
            Livre livreRendu = empruntServiceClient.livreRendu(token, Integer.parseInt(empruntId));
            model.addAttribute("livreRendu", livreRendu);
            model.addAttribute("message", "oui");

        } catch (Exception e){
            logger.error("Erreur :" + e);
            return null;

        }
        return "admin";


    }



}
