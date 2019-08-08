package com.biblioP7.webController;

import com.biblioP7.beans.Emprunt;
import com.biblioP7.beans.Membre;
import com.biblioP7.dao.MembreDao;
import com.biblioP7.feignClient.EmpruntServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class DashBoardController {

    @Autowired
    EmpruntServiceClient empruntServiceClient;


    @GetMapping("/client/dashboard")
    public String senregistrerForm(Model model) {

        Membre testMembre = new Membre();
        //System.out.println(testMembre);

        List<Emprunt> listeEmprunts = empruntServiceClient.empruntsParMembre(1);

        model.addAttribute("membre", testMembre);
        model.addAttribute("listeEmprunts", listeEmprunts);
        return "dashboard";
    }

    @GetMapping("/client/prolongerEmprunt")
    public String prolongerEmprunt(Model model, String empruntId) {
        System.out.println("on veut prolonger l'emprunt n° " + empruntId);
        Emprunt emprunt = empruntServiceClient.detailEmprunt(Integer.valueOf(empruntId));

        model.addAttribute("emprunt", emprunt);
        return "client/confirmerProlongation";
    }

    @GetMapping("/client/confirmerProlongation")
    public String confirmerProlongation(Model model, String empruntId) {
        try {
            empruntServiceClient.prolongerEmprunt(Integer.valueOf(empruntId), " " );
            return "redirect:/dashboard";

        } catch (Exception e){
            System.out.println("Erreur :" + e);
            return null;
        }
    }


}
