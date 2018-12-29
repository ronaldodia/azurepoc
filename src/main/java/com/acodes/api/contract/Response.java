package com.acodes.api.contract;

import com.acodes.api.model.Paiement;

public class Response {
	private String code;
	private String description;
	private Paiement paiement;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public Paiement getPaiement() {
		return paiement;
	}

	public void setPaiement(Paiement paiement) {
		this.paiement = paiement;
	}
	public Response(String code, String description, Paiement paiement) {
		super();
		this.code = code;
		this.description = description;
		this.paiement = paiement;
		
	}

}
