package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Ingrediente;
import com.example.demo.model.Piatto;
import com.example.demo.repository.PiattoRepository;

@Service
public class PiattoService {
	
	@Autowired 
	PiattoRepository piattoRepository;
	
	@Autowired
	IngredienteService is;
	
	
	public boolean alreadyExists(Piatto p) {
		if(piattoRepository.findByNome(p.getNome())!=null)
			return true;
		return false;
			
		
	}
	
	@Transactional
	public Piatto inserisci(Piatto piatto) {
		return this.piattoRepository.save(piatto);
	}
	
	public void inserisciPiattoIngrediente(/*Piatto piatto, si potrebbe collegare anche diretto fuori così non devo
	passarlo ogni volta*/ Ingrediente i) {
		this.is.inserisci(i);
		
	}
	
	@Transactional
	public void rimuovi(Piatto piatto) {
		this.piattoRepository.delete(piatto);
	}
	
	@Transactional
	public void clear() {
		this.piattoRepository.deleteAll();
	}
}