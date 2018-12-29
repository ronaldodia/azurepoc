package com.acodes.api.controller;

import java.util.ArrayList;

import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acodes.api.contract.PaiementDTO;
import com.acodes.api.contract.Response;
import com.acodes.api.model.Carte;
import com.acodes.api.model.Paiement;
import com.acodes.api.service.PaiementService;
import com.acodes.api.validator.Validator;

@RestController
public class PaiementController {

	@Autowired
	public PaiementService paiementService;
	@Autowired
	public Validator validator;

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
			return new ResponseEntity<Response>(
					new Response("BRE-0001", "Vous n'avez pas assez de crédit pour payer la facture", new Paiement()),
					HttpStatus.OK);
		}

		// si la somme des cartes est supérieure au montant de la facture, faire le
		// paiement,
		// sinon retourner une erreur (use case a discuter, si on veut faire des
		// paiements partiels)
		// if(true) throw new ValidationException("test error");
		/*
		 * ArrayList<Carte> cartes = new ArrayList<>(); Carte c1 = new Carte();
		 * 
		 * c1.setNumeroCarte(123456780); Carte c2 = new Carte();
		 * 
		 * c2.setNumeroCarte(123456781); cartes.add(c1); cartes.add(c2);
		 */
		// paiementService.payer(paiement);
		// System.out.println(paiementService.sommeParListeCarte(cartes));

	}

}
