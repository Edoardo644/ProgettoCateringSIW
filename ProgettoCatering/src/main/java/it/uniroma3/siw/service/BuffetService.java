package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Buffet;
import it.uniroma3.siw.repository.BuffetRepository;

@Service
public class BuffetService {
	
	@Autowired
	BuffetRepository buffetRepository;
	

	public boolean alreadyExists(Buffet p) {
		if(this.buffetRepository.findByNome(p.getNome())!=null)
			return true;
		return false;
	}
	
	public Buffet inserisci(Buffet buffet) {
		return this.buffetRepository.save(buffet);
	}
}
