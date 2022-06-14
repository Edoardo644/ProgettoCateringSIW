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
import com.example.demo.model.Piatto;
import com.example.demo.service.BuffetService;
import com.example.demo.service.PiattoService;

@Controller
public class PiattoController {

	@Autowired
	PiattoService piattoService;
	@Autowired 
	PiattoValidator piattoValidator;
	@Autowired
	BuffetService buffetService;
	
	
	@PostMapping("/admin/piatto")
	public String addPiatto(@Valid @ModelAttribute("piatto") Piatto p, BindingResult bindingResult, Model model) {
		this.piattoValidator.validate(p, bindingResult);
		if (!bindingResult.hasErrors()) {
			this.piattoService.inserisci(p);
			model.addAttribute("piatto", this.piattoService.searchById(p.getId()));
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


	@GetMapping("/admin/piattoForm")
	public String getPiattoForm(Model model) {
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

	@GetMapping("/admin/deletePiatti")
	public String deletePiatti(@RequestParam Long piattoId) {
		this.piattoService.rimuovi(piattoId);
		return "redirect:/elencoPiatti";
	}
	
	@GetMapping("/admin/updatePiatto")
    public String updatePiattoForm(@RequestParam Long piattoId, Model model) {
        System.out.println("L'id del piatto: " + piattoId);
        model.addAttribute("piatto", this.piattoService.searchById(piattoId));
        model.addAttribute("buffetDisponibili", this.buffetService.findAllBuffet());
        return "piattoUpdateForm.html";
    }

	
	@PostMapping("/piattoUpdate/{id}")
    public String updatePiatto(@Valid @ModelAttribute("piatto") Piatto piatto, BindingResult bindingResult, Model model) {
        this.piattoValidator.validate(piatto, bindingResult);
        if(!bindingResult.hasErrors()) {
            this.piattoService.inserisci(piatto);
            model.addAttribute("piatto", piatto);
            return "piatto.html";
        }
        else {
            return "piattoUpdateForm.html";
        }
    }
	
}
