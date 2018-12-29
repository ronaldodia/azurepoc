package com.acodes.api.validator;



import javax.validation.ValidationException;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import com.acodes.api.contract.PaiementDTO;
import com.acodes.api.model.Paiement;

@Component
public class Validator {

	

    public void validate(Paiement paiementDTO, Errors errors) {
     
            paiementDTO.getCartes().stream().forEach(c->{if(c.getNumeroCarte()==0) errors.reject("numeroCarte");
            		});
            if(errors.hasErrors()) {
            	throw new ValidationException(errors.toString());
            }
        
    }
}
