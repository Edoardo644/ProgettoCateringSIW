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

import com.example.demo.controller.validator.IngredienteValidator;
import com.example.demo.model.Ingrediente;
import com.example.demo.model.Piatto;
import com.example.demo.service.IngredienteService;
import com.example.demo.service.PiattoService;


@Controller
public class IngredienteController {
	
	@Autowired
	IngredienteService ingredienteService;
	@Autowired
	IngredienteValidator ingredienteValidator;
	@Autowired
	PiattoService piattoService;
	
	
	@PostMapping("/ingrediente")
	public String addIngrediente(@Valid @ModelAttribute("ingrediente") Ingrediente i, BindingResult bindingResult, Model model) {
		this.ingredienteValidator.validate(i, bindingResult);
		if (!bindingResult.hasErrors()) {
			this.ingredienteService.inserisci(i);
			model.addAttribute("ingrediente", this.ingredienteService.searchById(i.getId()));
			return "ingrediente.html";

		} else {
			return "ingredienteForm.html";
		}
	}

	// Richiede tutti gli ingredienti
	@GetMapping("/elencoIngredienti")
	public String getAllIngredienti(Model model) {
		List<Ingrediente> elencoIngredienti = this.ingredienteService.findAllIngredienti();
		model.addAttribute("elencoIngredienti", elencoIngredienti);
		return "elencoIngredienti.html";
	}


	@GetMapping("/ingredienteForm")
	public String getIngredientiForm(Model model) {
		model.addAttribute("ingrediente", new Ingrediente());
		List<Piatto> piattiDisponibili =this.piattoService.findAllPiatti();
		model.addAttribute("piattiDisponibili", piattiDisponibili);
		return "ingredienteForm.html";
	}


	@GetMapping("/ingrediente/{id}")
	public String getIngrediente(@PathVariable("id") Long id, Model model){
		Ingrediente ingrediente = this.ingredienteService.searchById(id);
		model.addAttribute("ingrediente", ingrediente);
		return "ingrediente.html";
	}

	@GetMapping("/deleteIngredienti")
	public String deleteIngredienti(@RequestParam Long ingredienteId) {
		this.ingredienteService.rimuovi(ingredienteId);
		return "redirect:/elencoIngredienti";
	}

}
