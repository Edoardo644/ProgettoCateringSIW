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
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.controller.validator.BuffetValidator;
import com.example.demo.model.Buffet;
import com.example.demo.service.BuffetService;

@Controller
public class BuffetController {
	@Autowired
	BuffetService buffetService;
	@Autowired
	BuffetValidator buffetValidator;

	/*
	 * ovviamente non ci sono solo le pagine per il piatto e il piatto form quindi
	 * poi andranno modificati i return e il path su cui trovare questa richiesta
	 * 
	 * @PostMapping("/buffet")
	 * //modelAttribute serve per associare questo oggetto con quello col nome
	 * specificato dentro il modello
	 * public String addBuffet(@Valid @ModelAttribute("buffet") Buffet
	 * b,BindingResult bindingResult,Model model){
	 * if(!bindingResult.hasErrors()) {
	 * this.buffetService.inserisci(b);
	 * model.addAttribute("buffet", model);
	 * return "buffet.html";
	 * 
	 * }else {
	 * return "buffetForm.html";
	 * }
	 * }
	 */

	@PostMapping("/buffet")
	public String addBuffet(@Valid @ModelAttribute("buffet") Buffet b, BindingResult bindingResult, Model model) {
		this.buffetValidator.validate(b, bindingResult);
		if (!bindingResult.hasErrors()) {
			this.buffetService.inserisci(b);
			model.addAttribute("buffet", this.buffetService.searchById(b.getId()));
			return "buffet.html";

		} else {
			return "buffetForm.html";
		}
	}
	
	// Richiede tutti i buffet
		@GetMapping("/elencoBuffet")
		public String getAllBuffet(Model model) {
			List<Buffet> elencoBuffet = this.buffetService.findAllBuffet();
			model.addAttribute("elencoBuffet", elencoBuffet);
			return "elencoBuffet.html";
		}
	

	@GetMapping("/buffetForm")
	public String getBuffetForm(Model model) {
		model.addAttribute("buffet", new Buffet());
		return "buffetForm.html";
	}
	
	
	@GetMapping("/buffet/{id}")
	public String getBuffet(@PathVariable("id") Long id, Model model){
		Buffet buffet = this.buffetService.searchById(id);
		model.addAttribute("buffet", buffet);
		model.addAttribute("elencoPiatti", buffet.getListaPiatti());
		return "buffet.html";
	}
	
	@GetMapping("/deleteBuffet")
    public String deleteBuffet(@RequestParam Long buffetId) {
        this.buffetService.rimuovi(buffetId);
        return "redirect:/elencoBuffet";
    }
	
	// Richiede tutti i piatti relativi a un buffet
			@GetMapping("/elencoPiatti")
			public String getAllPiattiFromBuffet(Model model) {
				List<Piatto> elencoPiatti = this.buffetService.findAllBuffet();
				model.addAttribute("elencoPiatti", elencoPiatti);
				return "elencoBuffet.html";
			}
	
	/*
	@GetMapping("/showUpdateForm")
	public String showUpdateForm(@RequestParam Long buffetId, Model model) {
		model.addAttribute("buffet", this.buffetService.searchById(buffetId));
		return "updateBuffetForm.html";
	}
	
	
	@PostMapping("/updateBuffet")
	public String addUpdateBuffet(@Valid @ModelAttribute("buffet") Buffet b, BindingResult bindingResult, Model model) {
		this.buffetValidator.validate(b, bindingResult);
		if (!bindingResult.hasErrors()) {
			Buffet updatedBuffet = new Buffet();
			updatedBuffet.setDescr(this.buffetService.searchById(b.getId()).getDescr());
			updatedBuffet.setNome(this.buffetService.searchById(b.getId()).getNome());
			this.buffetService.inserisci(updatedBuffet);
			model.addAttribute("buffet",updatedBuffet);
			return "buffet.html";

		} else {
			return "buffetForm.html";
		}
	}*/
	
	
}
