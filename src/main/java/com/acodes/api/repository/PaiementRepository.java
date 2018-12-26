package com.acodes.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.acodes.api.model.Paiement;

public interface PaiementRepository extends CrudRepository<Paiement, Integer> {

}
