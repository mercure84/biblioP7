package com.biblioP7.webController;

import com.biblioP7.beans.Membre;
import com.biblioP7.dao.MembreDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashBoardController {


    @RequestMapping("/dashboard")
    public String senregistrerForm (Model model){
        model.addAttribute("membre", new Membre());
        return "dashboard";
}

}
