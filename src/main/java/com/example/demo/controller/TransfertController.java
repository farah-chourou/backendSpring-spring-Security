package com.example.demo.controller;

import com.example.demo.entities.Agence;
import com.example.demo.entities.Client;
import com.example.demo.services.AgenceService;
import com.example.demo.services.ClientService;
import com.example.demo.services.TransfertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/transfert")// L'URL de base pour ce contrôleur est "/transfert"
@CrossOrigin(origins="http://localhost:3000")
public class TransfertController {


    @Autowired
    private TransfertService Ts; // Injection de dépendance du service transfert



    @PostMapping("/Pret/montant/{clientId}/{pretId}")// Cette méthode est appelée lorsqu'un formulaire avec la méthode POST est soumis à "/transfert/montant"
    public ResponseEntity<String> transfererMontant(@RequestParam double montant, // Récupère la valeur "montant" du formulaire
                                    @PathVariable("clientId") long clientId,
                                                    @PathVariable("pretId") long pretId) { // Récupère l'ID du client sélectionnée dans le formulaire

      Ts.transfertPret(clientId,montant,pretId);
        System.out.println(montant);
return ResponseEntity.status(HttpStatus.OK)
        .body("success");
    }
    @PostMapping("/Subvention/montant/{clientId}/{subId}" +
            "")// Cette méthode est appelée lorsqu'un formulaire avec la méthode POST est soumis à "/transfert/montant"
    public ResponseEntity<String> transfererMontantSub(@RequestParam double montant, // Récupère la valeur "montant" du formulaire
                                                    @PathVariable("clientId") long clientId,
                                                       @PathVariable("subId") long id_sub) { // Récupère l'ID du client sélectionnée dans le formulaire

        Ts.transfertSub(clientId,montant,id_sub);
        System.out.println(montant);
        return ResponseEntity.status(HttpStatus.OK)
                .body("success");
    }


    @PostMapping("/Projet/montant/{demandeid}")// Cette méthode est appelée lorsqu'un formulaire avec la méthode POST est soumis à "/transfert/montant"
    public ResponseEntity<String> transfererMontantProjet(@PathVariable("demandeid") long demandeid) { // Récupère l'ID du client sélectionnée dans le formulaire

        Ts.transfertProjet(demandeid);

        return ResponseEntity.status(HttpStatus.OK)
                .body("success");
    }


    @PostMapping("RembourserPret/{pretId}")
    public ResponseEntity<String> rembourserPret(@RequestParam double montant,
                                                 @PathVariable("pretId") long pretId){

        Ts.rembourserPret(pretId,montant);
        System.out.println(montant);
        return ResponseEntity.status(HttpStatus.OK)
                .body("success");

    }
    @PostMapping("RembourserProjet/{projetId}")
    public ResponseEntity<String> rembourserProjet(@RequestParam double montant,
                                                 @PathVariable("projetId") long projetId
                                                     ){

        Ts.rembourserProjet(projetId,montant);
        System.out.println(montant);
        return ResponseEntity.status(HttpStatus.OK)
                .body("success");

    }


}