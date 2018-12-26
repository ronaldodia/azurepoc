package com.acodes.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acodes.api.model.Carte;
import com.acodes.api.model.Paiement;
import com.acodes.api.repository.CarteRepository;
import com.acodes.api.repository.PaiementRepository;

@Service
public class PaiementService {
	@Autowired
	public PaiementRepository paiementRepository;
	@Autowired
	public CarteRepository carteRepository;

	public Paiement payer(Paiement paiement) {
		return paiementRepository.save(paiement);
	}

	public boolean carteEstValide(Carte carte) {
		return carteRepository.findByNumeroCarte(carte).size() == 0;
	}

	public int sommeParListeCarte(List<Carte> cartes) {
		List<Integer> listInt = new ArrayList<>();
		cartes.stream().forEach(c->{listInt.add(c.getNumeroCarte());});
		return carteRepository.findByNumeroCarteIn(listInt).stream().mapToInt(c -> c.getMontant()).sum();
	}
}
