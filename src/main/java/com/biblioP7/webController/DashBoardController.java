package com.biblioP7.webController;

import com.biblioP7.beans.Emprunt;
import com.biblioP7.beans.Membre;
import com.biblioP7.dao.MembreDao;
import com.biblioP7.feignClient.EmpruntServiceClient;
import com.biblioP7.feignClient.MembreServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class DashBoardController {

    @Autowired
    EmpruntServiceClient empruntServiceClient;

    @Autowired
    MembreServiceClient membreServiceClient;


    @GetMapping("/client/dashboard")
    public String afficherDashBoard(Model model, HttpSession session) {

        //System.out.println(testMembre);
        String token = session.getAttribute("token").toString();
        String email = session.getAttribute("membreEmail").toString();
        int id = Integer.valueOf(session.getAttribute("membreId").toString());

        Membre membre = membreServiceClient.dataMembre(token, email);
        List<Emprunt> listeEmprunts = empruntServiceClient.empruntsParMembre(token, id);
        model.addAttribute("membre", membre);
        model.addAttribute("listeEmprunts", listeEmprunts);
        return "dashboard";
    }

    @GetMapping("/client/prolongerEmprunt")
    public String prolongerEmprunt(Model model, String empruntId, HttpSession session) {
        String token = session.getAttribute("token").toString();

        System.out.println("on veut prolonger l'emprunt n° " + empruntId);
        Emprunt emprunt = empruntServiceClient.detailEmprunt(token, Integer.valueOf(empruntId));
        System.out.println("Emprunt à prolonger = " + emprunt);
        model.addAttribute("emprunt", emprunt);
        return "confirmerProlongation";
    }

    @GetMapping("/client/confirmerProlongation")
    public String confirmerProlongation(Model model, String empruntId, HttpSession session) {
        String token = session.getAttribute("token").toString();

        try {
            empruntServiceClient.prolongerEmprunt(token, Integer.valueOf(empruntId));
            return "redirect:/client/dashboard";

        } catch (Exception e){
            System.out.println("Erreur :" + e);
            return null;
        }
    }


}
