package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Transactional
    public void modificaNome(Buffet b, String nomeNuovo) {
		b.setNome(nomeNuovo);
        //prima modifichi nome con setNome()
        this.buffetRepository.modificaNomeApp(b.getNome(), b.getId());
    }

    @Transactional
    public void modificaDescr(Buffet b, String descrNuovo) {
    	b.setDescr(descrNuovo);
        //prima modifichi nome con setDescr()
        this.buffetRepository.modificaDescrApp(b.getDescr(), b.getId());
    }
	
	
}
