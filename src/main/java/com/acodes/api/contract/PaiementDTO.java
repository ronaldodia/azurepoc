package com.acodes.api.contract;

import java.util.List;

import com.acodes.api.model.Carte;

public class PaiementDTO {
	
	private String codeReference;
	private String fournisseurService;
	private String numeroFacture;
	private int montantPaye;
	private List<Carte> cartes;
	public String getCodeReference() {
		return codeReference;
	}
	public void setCodeReference(String codeReference) {
		this.codeReference = codeReference;
	}
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
	public int getMontantPaye() {
		return montantPaye;
	}
	public void setMontantPaye(int montantPaye) {
		this.montantPaye = montantPaye;
	}
	public List<Carte> getCartes() {
		return cartes;
	}
	public void setCartes(List<Carte> cartes) {
		this.cartes = cartes;
	}
	
}
