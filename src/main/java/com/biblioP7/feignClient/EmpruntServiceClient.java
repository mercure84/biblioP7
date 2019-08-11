package com.biblioP7.feignClient;

import com.biblioP7.beans.CreationEmprunt;
import com.biblioP7.beans.Emprunt;
import com.biblioP7.beans.Livre;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="emprunt-service", url="http://localhost:8080")
public interface EmpruntServiceClient {


    @RequestMapping(value="/api/listeEmprunts", method= RequestMethod.GET)
    List<Emprunt> listeEmprunts();

    @RequestMapping(value="/api/EmpruntsMembre/{membreId}", method = RequestMethod.GET)
    List<Emprunt> empruntsParMembre(@RequestHeader("Authorization") String token, @PathVariable int membreId);

    @GetMapping(value="/api/Emprunt/{empruntId}")
    Emprunt detailEmprunt(@PathVariable int empruntId);


    @PostMapping(value="/api/creerEmprunt")
    Emprunt creerEmprunt(@RequestBody CreationEmprunt creationEmprunt);


    @RequestMapping(value="/api/prolongerEmprunt/{id}", method= RequestMethod.GET)
    Emprunt prolongerEmprunt(@RequestHeader("Authorization") String token, @PathVariable int id);


    @RequestMapping(value="/api/stopperEmprunt/{id}", method = RequestMethod.GET)
    Livre livreRendu(@PathVariable int id);


    @CrossOrigin("*")
    @RequestMapping(value="/api/listeEmpruntsExpires", method= RequestMethod.GET)
    List<Emprunt> empruntsExpires ();



}
