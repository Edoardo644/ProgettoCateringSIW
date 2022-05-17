package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Piatto;
import it.uniroma3.siw.repository.PiattoRepository;

@Service
public class PiattoService {
	
	@Autowired 
	PiattoRepository piattoRepository;
	
	
	public boolean alreadyExists(Piatto p) {
		if(piattoRepository.findByNome(p.getNome())!=null)
			return true;
		return false;
			
		
	}
	
	public Piatto inserisci(Piatto piatto) {
		return this.piattoRepository.save(piatto);
	}
}
