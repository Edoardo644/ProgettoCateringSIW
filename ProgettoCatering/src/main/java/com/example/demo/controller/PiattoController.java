package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.controller.validator.PiattoValidator;
import com.example.demo.model.Piatto;
import com.example.demo.service.PiattoService;

@Controller
public class PiattoController {

	@Autowired
	PiattoService piattoService;
	@Autowired 
	PiattoValidator piattoValidator;
	
	/* ovviamente non ci sono solo le pagine per il piatto e il piatto form quindi poi andranno modificati i return e il path su cui trovare questa richiesta
	@PostMapping("/piatto")
	//modelAttribute serve per associare questo oggetto con quello col nome specificato dentro il modello
	public String addPiatto(@Valid @ModelAttribute("piatto") Piatto p,BindingResult bindingResult,Model model){
		if(!bindingResult.hasErrors()) {
			this.piattoService.inserisci(p);
			model.addAttribute("piatto", model);
			return "piatto.html";
			
		}else {
			return "piattoForm.html";
		}
	}*/
	
	
	/*
	inserisciPiattoConIngredienti(Ingrediente i,Piatto p){
		int val=0;
		while(val==0) {
			inserisciPiattoIngrediente(i);
			p.getIngredientiDelPiatto().add(i);
			//if(bottonePremuto) val=1
		}
		this.piattoService.inserisci(p);
		
	}*/
}
