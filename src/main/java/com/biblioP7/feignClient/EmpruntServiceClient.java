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

    @RequestMapping(value="/api/EmpruntsMembre/{membreId}")
    List<Emprunt> empruntsParMembre(@PathVariable int membreId);



    @PostMapping(value="/api/creerEmprunt")
    Emprunt creerEmprunt(@RequestBody CreationEmprunt creationEmprunt);


    @RequestMapping(value="/api/prolongerEmprunt/{id}", method= RequestMethod.GET)
    Emprunt prolongerEmprunt(@PathVariable int id, @RequestHeader("Authorization") String token);


    @RequestMapping(value="/api/stopperEmprunt/{id}")
    Livre livreRendu(@PathVariable int id);


    @CrossOrigin("*")
    @RequestMapping(value="/api/listeEmpruntsExpires", method= RequestMethod.GET)
    List<Emprunt> empruntsExpires ();


}
