package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.demo.controller.validator.BuffetValidator;
import com.example.demo.service.BuffetService;

@Controller
public class BuffetController {
	@Autowired
	BuffetService buffetService ;
	@Autowired
	BuffetValidator buffetValidator; 

	/* ovviamente non ci sono solo le pagine per il piatto e il piatto form quindi poi andranno modificati i return e il path su cui trovare questa richiesta
	@PostMapping("/buffet")
	//modelAttribute serve per associare questo oggetto con quello col nome specificato dentro il modello
	public String addBuffet(@Valid @ModelAttribute("buffet") Buffet b,BindingResult bindingResult,Model model){
		if(!bindingResult.hasErrors()) {
			this.buffetService.inserisci(b);
			model.addAttribute("buffet", model);
			return "buffet.html";
			
		}else {
			return "buffetForm.html";
		}
	}*/
}
