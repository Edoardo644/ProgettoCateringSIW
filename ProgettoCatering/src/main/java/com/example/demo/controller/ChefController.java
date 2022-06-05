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

import com.example.demo.controller.validator.ChefValidator;
import com.example.demo.model.Chef;
import com.example.demo.service.ChefService;

@Controller
public class ChefController {
	
	@Autowired
	ChefService chefService;
	@Autowired
	ChefValidator chefValidator;
	
	/* ovviamente non ci sono solo le pagine per il piatto e il piatto form quindi poi andranno modificati i return e il path su cui trovare questa richiesta
	@PostMapping("/chef")
	//modelAttribute serve per associare questo oggetto con quello col nome specificato dentro il modello
	public String addChef(@Valid @ModelAttribute("chef") Chef c,BindingResult bindingResult,Model model){
		if(!bindingResult.hasErrors()) {
			this.chefService.inserisci(c);
			model.addAttribute("chef", model);
			return "chef.html";
			
		}else {
			return "chefForm.html";
		}
	}*/
	

	@PostMapping("/chef")
	public String addChef(@Valid @ModelAttribute("chef") Chef c, BindingResult bindingResult, Model model) {
		this.chefValidator.validate(c, bindingResult);
		if (!bindingResult.hasErrors()) {
			this.chefService.inserisci(c);
			model.addAttribute("chef", this.chefService.searchById(c.getId()));
			return "chef.html";

		} else {
			return "chefForm.html";
		}
	}

	// Richiede tutti i piatti
	@GetMapping("/elencoChef")
	public String getAllChef(Model model) {
		List<Chef> elencoChef = this.chefService.findAllChef();
		model.addAttribute("elencoChef", elencoChef);
		return "elencoChef.html";
	}


	@GetMapping("/chefForm")
	public String getChefForm(Model model) {
		model.addAttribute("chef", new Chef());
		return "chefForm.html";
	}


	@GetMapping("/chef/{id}")
	public String getChef(@PathVariable("id") Long id, Model model){
		Chef chef = this.chefService.searchById(id);
		model.addAttribute("chef", chef);
		model.addAttribute("elencoChef", chef.getBuffetDelloChef());
		return "chef.html";
	}

	@GetMapping("/deleteChef")
	public String deleteChef(@RequestParam Long chefId) {
		this.chefService.rimuovi(chefId);
		return "redirect:/elencoChef";
	}
}
