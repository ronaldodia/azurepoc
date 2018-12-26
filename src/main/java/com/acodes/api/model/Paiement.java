package com.acodes.api.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Paiement {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String codeReference;
	private String fournisseurService;
	private String numeroFacture;
	private int montantPaye;
	@OneToMany
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

	public int getId() {
		return id;
	}

}
