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


    @GetMapping("/dashboard")
    public String senregistrerForm (Model model){

        Membre testMembre = new Membre();
        //System.out.println(testMembre);

        List<Emprunt> listeEmprunts = empruntServiceClient.empruntsParMembre(1);

        model.addAttribute("membre", testMembre);
        return "dashboard";
}

}
