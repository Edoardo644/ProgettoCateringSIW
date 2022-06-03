package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.controller.validator.PiattoValidator;
import com.example.demo.model.Buffet;
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
	
	@PostMapping("/piatto")
	public String addPiatto(@Valid @ModelAttribute("piatto") Piatto p, BindingResult bindingResult, Model model) {
		this.piattoValidator.validate(p, bindingResult);
		if (!bindingResult.hasErrors()) {
			this.piattoService.inserisci(p);
			model.addAttribute("buffet", this.piattoService.searchById(p.getId()));
			return "piatto.html";

		} else {
			return "piattoForm.html";
		}
	}

	// Richiede tutti i piatti
	@GetMapping("/elencoPiatti")
	public String getAllPiatti(Model model) {
		List<Piatto> elencoPiatti = this.piattoService.findAllPiatti();
		model.addAttribute("elencoPiatti", elencoPiatti);
		return "elencoPiatti.html";
	}


	@GetMapping("/piattoForm")
	public String getBuffetForm(Model model) {
		model.addAttribute("piatto", new Piatto());
		return "piattoForm.html";
	}


	@GetMapping("/piatto/{id}")
	public String getPiatto(@PathVariable("id") Long id, Model model){
		Piatto piatto = this.piattoService.searchById(id);
		model.addAttribute("piatto", piatto);
		model.addAttribute("elencoIngredienti", piatto.getIngredientiDelPiatto());
		return "piatto.html";
	}

	@GetMapping("/deletePiatti")
	public String deletePiatti(@RequestParam Long piattoId) {
		this.piattoService.rimuovi(piattoId);
		return "redirect:/elencoPiatti";
	}

//	//Richiede tutti i piatti relativi a un buffet
//	@GetMapping("/elencoPiatti")
//	public String getAllPiattiFromBuffet(Model model) {
//		List<Piatto> elencoPiatti = this.buffetService.findAllBuffet();
//		model.addAttribute("elencoPiatti", elencoPiatti);
//		return "elencoBuffet.html";
//	}

//
//	@GetMapping("/buffet/addPiatto")
//	public String addPiattoAlBuffet(@RequestParam Long buffetId) {
//		Buffet buffet = this.buffetService.searchById(buffetId);
//		buffet.getListaPiatti().add(null);
//		return "redirect:/elencoBuffet";
//	}
//	
//	@GetMapping("/buffet/addPiattoABuffet")
//	public String showListPiattiToAdd() {
//		return "piattiToAdd.html";
//	}
	
}
