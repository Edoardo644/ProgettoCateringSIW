package com.example.demo.controller;

import javax.validation.Valid;

import com.example.demo.controller.validator.BuffetValidator;
import com.example.demo.service.BuffetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
			model.addAttribute("buffet", model);
			return "buffet.html";

		} else {
			return "buffetForm.html";
		}
	}

	@GetMapping("/buffetForm")
	public String getBuffetForm(Model model) {
		model.addAttribute("buffet", new Buffet());
		return "buffetForm.html";
	}
}
