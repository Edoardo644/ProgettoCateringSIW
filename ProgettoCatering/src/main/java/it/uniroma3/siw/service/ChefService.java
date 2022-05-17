package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public Chef inserisci(Chef chef) {
		return this.chefRepository.save(chef);
	}
}
