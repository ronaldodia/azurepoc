package com.acodes.api.contract;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.acodes.api.model.Carte;

public class PaiementDTO {
	@NotNull
	private String fournisseurService;
	@NotNull
	private String numeroFacture;
	@NotEmpty
	@Valid
	private List<Carte> cartes;

	public String getFournisseurService() {
		return fournisseurService;
	}

	public void setFournisseurService(String fournisseurService) {
		this.fournisseurService = fournisseurService;
	}

	public String getNumeroFacture() {
		return numeroFacture;
	}

	public void setNumeroFacture(String numeroFacture) {
		this.numeroFacture = numeroFacture;
	}

	
	public List<Carte> getCartes() {
		if (cartes == null)
			return new ArrayList<Carte>();
		return cartes;
	}

	public void setCartes(List<Carte> cartes) {
		this.cartes = cartes;
	}

}
