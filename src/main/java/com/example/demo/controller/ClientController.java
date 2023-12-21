package com.example.demo.controller;

import com.example.demo.entities.Client;
import com.example.demo.entities.Fichier;
import com.example.demo.entities.Garant;
import com.example.demo.repository.FichierRepository;
import com.example.demo.services.ClientService;
import com.example.demo.services.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
//import org.springframework.http.MediaType;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/Client")

public class ClientController {
    @Autowired
    private ClientService cltS ;
    @Autowired
    FichierRepository fichierRepository;
    @Autowired
    private EmailService emailService;

    @PutMapping("/put/{id}")

    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client updatedClient) {

        Client client = cltS.updateClient(id, updatedClient);

        return ResponseEntity.ok(client);

    }


    @PostMapping(value = {"/ADDfichier/{id}"}, consumes={MediaType.MULTIPART_FORM_DATA_VALUE})
    public String addFichier ( @RequestPart("fichier") MultipartFile file,@PathVariable Long id) {

        Client f= cltS.getClientById(id);
        try{

            Fichier fichier= new Fichier(StringUtils.cleanPath(file.getOriginalFilename()), file.getContentType(), file.getBytes(), f);

            fichierRepository.save(fichier);
            return "fichier ajouter";
        }
        catch(Exception e){
         System.out.println(e.getMessage());
         return null;
        }

    }

    @PostMapping("/ADDClient")
    public Client addclient (@Validated @RequestBody @Valid Client f) {




        return cltS.AjouterClient(f);
    }





    @PostMapping("/UPDATEClient")
    public Client UpdateClient (@Validated @RequestBody @Valid Client f) {
        return cltS.ModifierClient(f);
    }

    @DeleteMapping("/DELETEClient/{id}")
    public void DeleteClient(@PathVariable Long id) {
        cltS.SupprimerClient(id);
    }

    @GetMapping("/GETALLClient")
    public List<Client> GETALLClient(){
        return cltS.ListClient();
    }

    @GetMapping("/GETCLIENTBYID/{id}")
    public Client GetClientById(@PathVariable Long id){
        return cltS.getClientById(id);
    }



}
   // Logger logger = LoggerFactory.getLogger(LoggingController.class);