package com.biblioP7.feignClient;

import com.biblioP7.beans.Membre;
import com.biblioP7.beans.RegisterForm;
import com.biblioP7.security.EncryptedPasswordUtils;
import com.biblioP7.security.JwtRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="membre-service", url="http://localhost:8080")
public interface MembreServiceClient {

    @RequestMapping(value="/api/listeMembres", method= RequestMethod.GET)
    List<Membre> listeMembres();



    @GetMapping(value="/api/Membre/data/{email}")
    Membre dataMembre(@PathVariable String email);



    @GetMapping(value="/api/Membre/{id}")
    Membre detailMembre(@PathVariable int id);


    @PostMapping(value="/api/ajouterMembre")
    Membre ajouterMembre(@RequestBody RegisterForm userForm);


    @PostMapping(value="/api/login")
    ResponseEntity<?> login(@RequestBody JwtRequest authenticationRequest);

}
