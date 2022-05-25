package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Buffet;
import com.example.demo.repository.BuffetRepository;

@Service
public class BuffetService {
	
	@Autowired
	BuffetRepository buffetRepository;
	

	public boolean alreadyExists(Buffet p) {
		if(this.buffetRepository.findByNome(p.getNome())!=null)
			return true;
		return false;
	}
	
	@Transactional
	public Buffet inserisci(Buffet buffet) {
		return this.buffetRepository.save(buffet);
	}
	
	@Transactional
	public void rimuovi(Buffet buffet) {
		this.buffetRepository.delete(buffet);
	}
	
	@Transactional
	public void clear() {
		this.buffetRepository.deleteAll();
	}
	
	
	
	
}
