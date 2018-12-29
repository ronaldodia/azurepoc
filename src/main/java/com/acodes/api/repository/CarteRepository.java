package com.acodes.api.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.acodes.api.model.Carte;

public interface CarteRepository extends CrudRepository<Carte, Integer>{

	List<Carte> findByNumeroCarte(int numeroCarte);
	List<Carte> findByNumeroCarteIn(List<Integer> carte);
	
}
