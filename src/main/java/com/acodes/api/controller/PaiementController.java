package com.acodes.api.controller;

import java.util.ArrayList;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acodes.api.model.Carte;
import com.acodes.api.model.Paiement;
import com.acodes.api.service.PaiementService;

@RestController
public class PaiementController {

	@Autowired
	public PaiementService paiementService;

	@PostMapping("/payer")
	public Paiement payer(@RequestBody Paiement paiement) {
		// verifier que les cartes existes
		
		// recuperer les sommes de toutes les cartes
		// verifier que la facture existe
		// si la somme des cartes est supérieure au montant de la facture, faire le
		// paiement,
		// sinon retourner une erreur (use case a discuter, si on veut faire des
		// paiements partiels)
		// if(true) throw new ValidationException("test error");
		ArrayList<Carte> cartes = new ArrayList<>();
		Carte c1 = new Carte();

		c1.setNumeroCarte(123456780);
		Carte c2 = new Carte();

		c2.setNumeroCarte(123456781);
		cartes.add(c1);
		cartes.add(c2);
		return paiementService.payer(paiement);
	}

}
