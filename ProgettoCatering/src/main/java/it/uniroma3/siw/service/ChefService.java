package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Buffet;
import it.uniroma3.siw.model.Chef;
import it.uniroma3.siw.repository.ChefRepository;

@Service
public class ChefService {
	
	@Autowired
	ChefRepository chefRepository;
	
	public boolean alreadyExists(Chef p) {
		if(this.chefRepository.findByNomeAndCognome(p.getNome(),p.getCognome())!=null)
			return true;
		return false;
	}
	
	@Transactional
	public Chef inserisci(Chef chef) {
		return this.chefRepository.save(chef);
	}
	
	@Transactional
	public void rimuovi(Chef chef) {
		this.chefRepository.delete(chef);
	}
	
	@Transactional
	public void clear() {
		this.chefRepository.deleteAll();
	}
}
