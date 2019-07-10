package com.biblioP7.controllers;


import com.biblioP7.beans.Emprunt;
import com.biblioP7.beans.Livre;
import com.biblioP7.beans.Membre;
import com.biblioP7.dao.EmpruntDao;
import com.biblioP7.dao.LivreDao;
import com.biblioP7.dao.MembreDao;
import com.biblioP7.beans.CreationEmprunt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
public class EmpruntController {

    @Autowired
    private EmpruntDao empruntDao;

    @Autowired
    private MembreDao membreDao;

    @Autowired
    private LivreDao livreDao ;



    @RequestMapping(value="/api/listeEmprunts", method= RequestMethod.GET)
    public List<Emprunt> listeEmprunts(){
        List<Emprunt> emprunts = empruntDao.findAll();
        return emprunts;
    }

    @RequestMapping(value="/api/EmpruntsMembre/{membreId}")
    public List<Emprunt> empruntsParMembre(@PathVariable int membreId){
        Membre membre = membreDao.findById(membreId);

        List<Emprunt> listeEmprunt = empruntDao.findEmpruntsByMembre(membre);

        return listeEmprunt;
    }



    @PostMapping(value="/api/creerEmprunt")
    public Emprunt creerEmprunt(@RequestBody CreationEmprunt creationEmprunt){

        System.out.println("dataJSON = " + creationEmprunt);
        int membreId = Integer.parseInt(creationEmprunt.getMembreId());
        int livreId = Integer.parseInt(creationEmprunt.getLivreId());

        System.out.println("livreId = " + livreId + "membreId = " + membreId) ;

        Date dateDebut = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime((dateDebut));
        c.add(Calendar.DATE, 28);
        Date dateFin = c.getTime();
        System.out.println("date début = " + dateDebut +", date de fin = " + dateFin);

        Membre membre = membreDao.findById(membreId);
        Livre livre = livreDao.findById(livreId);

        if (!livre.isDisponible()){
            //le livre n'est pas disponible, on ne peut pas l'emprunter !
            return null;

        }

        else {

            Emprunt nouvelEmprunt = new Emprunt();

            nouvelEmprunt.setDebutDate(dateDebut);
            nouvelEmprunt.setFinDate(dateFin);
            nouvelEmprunt.setLivre(livre);
            nouvelEmprunt.setMembre(membre);

            empruntDao.save(nouvelEmprunt);

            //passage et sauvegarde du livre en tant que non disponible (à modifier plus tard si nous gérons la quantité ?)
            livre.setDisponible(false);
            livreDao.save(livre);
            return nouvelEmprunt;
        }

    }

    @CrossOrigin("*")
    @RequestMapping(value="/api/prolongerEmprunt/{id}", method= RequestMethod.GET)
    public Emprunt prolongerEmprunt(@PathVariable int id){

        Emprunt empruntAProlonger = empruntDao.findById(id);

        if(empruntAProlonger.isProlonge()){
            return null;

        } else {
            empruntAProlonger.setProlonge(true);
            //ajout de 28 jours à dateFin
            Date dateFin = empruntAProlonger.getFinDate();
            Calendar c = Calendar.getInstance();
            c.setTime(dateFin);
            c.add(Calendar.DATE, 28);
            Date dateFinBis = c.getTime();

            empruntAProlonger.setFinDate(dateFinBis);
            empruntDao.save(empruntAProlonger);
            System.out.println("l'emprunt n° " + empruntAProlonger.getId() + " a bien été prolongé !");
            return empruntAProlonger;

        }


    }

    @CrossOrigin("*")
    @RequestMapping(value="/api/stopperEmprunt/{id}")
    public Livre livreRendu(@PathVariable int id){

        //on tope l'emprunt à "rendu true" + modif de la date de fin
        Emprunt emprunt = empruntDao.findById(id);
        emprunt.setFinDate(new Date());
        emprunt.setRendu(true);

        //on tope le livre à disponible = true
        Livre livreRendu = livreDao.findById(emprunt.getLivre().getId());
        livreRendu.setDisponible(true);

        //on save les 2 entités livre / emprunt
        livreDao.save(livreRendu);
        empruntDao.save(emprunt);

        return livreRendu;


    }


    @CrossOrigin("*")
    @RequestMapping(value="/api/listeEmpruntsExpires", method= RequestMethod.GET)
    public List<Emprunt> empruntsExpires (){
        Date today = new Date();
        List<Emprunt> emprunts = empruntDao.findEmpruntsExpires(false, today);
        return emprunts;
    }


//    IMPLEMENTATION DU BATCH DE RELANCE ==> création d'un fichier avec la liste des mails à envoyer !

    @CrossOrigin
    @RequestMapping(value="/api/runBatch", method=RequestMethod.GET)
    public String batch(){
        String resultat = null;
        try {

            List<Emprunt> empruntsEchusEncours = empruntDao.findEmpruntsExpires(false, new Date());

            List<String> messages = new ArrayList<String>();

            if (empruntsEchusEncours.size() == 0){
                messages.add("AUCUN MEMBRE A RELANCER :)");
            } else {
                for(int i = 0; i<empruntsEchusEncours.size(); i++){

                    messages.add(genererMail(empruntsEchusEncours.get(i)));

                }
            }

            fichierMails(empruntsEchusEncours, messages);
            resultat = "Le batch a été exécuté sans erreur, vous pouvez consulter le fichier de retour dans le dossier habituel";
                    } catch(Exception error){
            resultat = "Le batch a échoué !" + error;
        }
        return resultat;
    }


    private String genererMail(Emprunt emprunt) {
        Membre membre = emprunt.getMembre();
        Livre livre = emprunt.getLivre();
        String texte = "\n[SEND TO :" + membre.getEmail() + "] \n"
                +"Bonjour " + membre.getPrenom() + membre.getNom() + ", \n"+
                "Votre bibliothèque attend le retour du livre : " + livre.getTitre() + ", que vous avez emprunté en date du " + emprunt.getDebutDate() + ". \n" +
                "Ce livre aurait du être restitué avant le " + emprunt.getFinDate() + ". \n" +
                "Nous vous prions de nous le rapporter dans les meilleurs délais pour que d'autres lecteurs puissent emprunter cet ouvrage. \n" +
                "En vous remerciant pour votre compréhension. \n" + "Votre bibliothécaire préféré. \n";


        return texte;
    }


    private void fichierMails(List<Emprunt> empruntsEchus, List<String> messages) throws IOException {
        System.out.println("Emprunts échus : \n" + empruntsEchus);
        System.out.println("Messages générés : \n" + messages);
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = dateFormat.format(new Date());
            FileWriter writer = new FileWriter("mails"+dateString+".txt");
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write(empruntsEchus.toString());
            bw.write(messages.toString());
            bw.close();

        } catch (IOException error){
            System.out.println("IO Exception interceptée : " + error);
        }

    }


}
