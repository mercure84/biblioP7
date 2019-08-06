package com.biblioP7.feignClient;


import com.biblioP7.beans.Livre;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name="livre-service", url="http://localhost:8080")
public interface LivreServiceClient {


    @RequestMapping(value="/1api/Livre/listeLivres", method= RequestMethod.GET)
    List<Livre> listeLivres();


    @RequestMapping(value="/1api/Livre/nbLivres", method= RequestMethod.GET)
    Map<String, Integer> nbLivres();


    @GetMapping(value="/1api/Livre/{id}")
    Livre detailLivre(@PathVariable int id);

    @GetMapping(value="/1api/Livre/randomLivre")
    Livre randomLivreDispo();

    @GetMapping(value="/1api/Livre/filtrerLivres")
    List<Livre> filtrerLivres(@RequestParam(name="typeRecherche") String typeRecherche, @RequestParam(name="champRecherche") String champRecherche);

    @PostMapping(value="/1api/Livre/ajouterLivre")
    void ajouterLivre(@RequestBody Livre livre);

}
