package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import it.uniroma3.siw.controller.validator.IngredienteValidator;
import it.uniroma3.siw.service.IngredienteService;


@Controller
public class IngredienteController {
	
	@Autowired
	IngredienteService ingredienteService;
	@Autowired
	IngredienteValidator ingredienteValidator;
	
	/* ovviamente non ci sono solo le pagine per il piatto e il piatto form quindi poi andranno modificati i return e il path su cui trovare questa richiesta
	@PostMapping("/Ingrediente")
	//modelAttribute serve per associare questo oggetto con quello col nome specificato dentro il modello
	public String addIngrediente(@Valid @ModelAttribute("ingrediente") Ingrediente i,BindingResult bindingResult,Model model){
		if(!bindingResult.hasErrors()) {
			this.ingredienteService.inserisci(i);
			model.addAttribute("ingrediente", model);
			return "ingrediente.html";
			
		}else {
			return "ingredienteForm.html";
		}
	}*/

}
