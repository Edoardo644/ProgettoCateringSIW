package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import it.uniroma3.siw.controller.validator.ChefValidator;
import it.uniroma3.siw.service.ChefService;

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
}
