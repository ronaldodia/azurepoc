package com.acodes.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityNotFoundException;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acodes.api.model.Carte;
import com.acodes.api.model.Paiement;
import com.acodes.api.repository.CarteRepository;
import com.acodes.api.repository.PaiementRepository;
import com.acodes.api.util.Common;

@Service
public class PaiementService {
	private static final String SOMELEC = "SOMELEC";
	private static final String NUMERO_TEST1 = "FAC1";
	private static final String NUMERO_TEST2 = "FAC2";
	@Autowired
	private PaiementRepository paiementRepository;
	@Autowired
	private CarteRepository carteRepository;
	@Autowired
	private Common commonFunctions;

	public Paiement payer(Paiement paiement) {
		paiement.setCodeReference(commonFunctions.numReference());
		//enlever les paiements
		enleverlesPaiement(paiement.getCartes(),paiement.getMontantPaye());
		// mettre les id des cartes
		List<Carte> cartesAvecId = listeCartesParNumero(paiement.getCartes());
		paiement.setCartes(new ArrayList());
		// bug: modifier les cartes pour les lier au paiement
		cartesAvecId.stream().forEach(c -> {
			c.setPaiement(paiement);
		});
		// endbug:
		paiement.getCartes().addAll(cartesAvecId);
		return paiementRepository.save(paiement);
	}

	public boolean carteEstValide(Carte carte) {
		return carteRepository.findByNumeroCarte(carte.getNumeroCarte()).size() == 0;
	}

	public int sommeParListeCarte(List<Carte> cartes) {
		List<Carte> listeCarte = listeCartesParNumero(cartes);
		return sommeCartes(listeCarte);
	}

	private int sommeCartes(List<Carte> listeCarte) {
		return listeCarte.stream().mapToInt(c -> c.getMontant()).sum();
	}

	private List<Carte> listeCartesParNumero(List<Carte> cartes) {
		List<Integer> listNumCartes = new ArrayList<>();
		cartes.stream().forEach(c -> {
			listNumCartes.add(c.getNumeroCarte());
		});
		List<Carte> listeCarte = carteRepository.findByNumeroCarteIn(listNumCartes);
		return listeCarte;
	}

	public int recupererMontantFacture(String fournisseurService, String numeroFacture) {

		if (SOMELEC.equals(fournisseurService)
				&& (NUMERO_TEST1.equals(numeroFacture) || NUMERO_TEST2.equals(numeroFacture))) {
			return 6000;
		} else {
			throw new EntityNotFoundException("Votre facture est introuvable");
		}

	}

	/**
	 * Vérifier si la somme des cartes couvre le montant de la facture.
	 * 
	 * @param sommeCartes
	 * @param montantFacture
	 * @return boolean
	 */
	public boolean peutPayer(int sommeCartes, int montantFacture) {
		return sommeCartes >= montantFacture;
	}

	private void enleverlesPaiement(List<Carte> listeCartes, int montant) {

		for (Carte c : listeCartes) {
			// pour chaque carte, on recupere le montant dans la BD
			Carte carte = carteRepository.findByNumeroCarte(c.getNumeroCarte()).get(0);
			if(carte == null)continue;
			if (carte.getMontant() >= montant) {
				carte.setMontant(carte.getMontant() - montant);
				
			} else {
				montant = montant - carte.getMontant();
				carte.setMontant(0);
			}
			carteRepository.save(carte);
		}

	}
}
