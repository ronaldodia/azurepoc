package com.acodes.api.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

@Entity
public class Paiement {
	@ApiParam(hidden=true)
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ApiParam(hidden=true)
	
	private String codeReference;
	@ApiModelProperty(required = true)
	@NotNull
	private String fournisseurService;
	@ApiModelProperty(required = true)
	@NotNull
	private String numeroFacture;
	@ApiParam(hidden=true)
	
	private int montantPaye;
	@ApiModelProperty(required = true)
	@NotEmpty
	@JsonManagedReference
	@OneToMany(mappedBy="paiement", cascade=CascadeType.ALL)
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

	public List<Carte> getCartes() {
		return cartes;
	}

	public void setCartes(List<Carte> cartes) {
		this.cartes = cartes;
	}

}
