package com.acodes.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.acodes.api.contract.Response;
import com.acodes.api.model.Paiement;
import com.acodes.api.service.PaiementService;
import com.acodes.api.validator.Validator;

@RestController
public class PaiementController {

	@Autowired
	public PaiementService paiementService;
	@Autowired
	public Validator validator;

	@CrossOrigin(origins = "*", maxAge = 3600)
	@PostMapping("/payer")
	public ResponseEntity<Response> payer(@Valid @RequestBody Paiement paiementDTO, Errors errors) {
		// faire une validation
		validator.validate(paiementDTO, errors);
		// recuperer montant facture par numero de reference
		// simulatation
		int montantFacture = paiementService.recupererMontantFacture(paiementDTO.getFournisseurService(),
				paiementDTO.getNumeroFacture());
		// rajouter le montant dans le paiement
		paiementDTO.setMontantPaye(montantFacture);

		// recuperer les sommes de toutes les cartes
		int sommeCartes = paiementService.sommeParListeCarte(paiementDTO.getCartes());
		
			if (paiementService.peutPayer(sommeCartes, montantFacture)) {
				Paiement paiement = paiementService.payer(paiementDTO);
				Response response = new Response("INF-0001", "Le paiement s'est effectué avec succès!", paiement);
				return new ResponseEntity<Response>(response, HttpStatus.OK);
			} else {
				return new ResponseEntity<Response>(new Response("BRE-0001",
						"Vous n'avez pas assez de crédit pour payer la facture", null), HttpStatus.OK);
			}
		

	}

}
