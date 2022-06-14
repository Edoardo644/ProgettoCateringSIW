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

import com.example.demo.controller.validator.BuffetValidator;
import com.example.demo.model.Buffet;
import com.example.demo.model.Piatto;
import com.example.demo.service.BuffetService;
import com.example.demo.service.PiattoService;

@Controller
public class BuffetController {
	@Autowired
	BuffetService buffetService;
	@Autowired
	BuffetValidator buffetValidator;
	@Autowired
	PiattoService piattoService;
	
	Buffet buffetAppoggio = new Buffet();

	

	@PostMapping("/admin/buffet")
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


	@GetMapping("/admin/buffetForm")
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


	@PostMapping("/buffet/addPiattoABuffet")
	public String addPiattoABuffet(@RequestParam Long piattoId) {
		Piatto piatto = this.piattoService.searchById(piattoId);
		this.buffetAppoggio.getListaPiatti().add(piatto);
		return "piattiToAdd.html";
	}
	
	@GetMapping("/buffet/showPiattiToAdd")
	public String showPiattiToAdd(@RequestParam Long buffetId, Model model) {
		this.buffetAppoggio = this.buffetService.searchById(buffetId);
		List<Piatto> elencoPiatti = this.piattoService.findAllPiatti();
		model.addAttribute("elencoPiatti", elencoPiatti);
		return "piattiToAdd.html";
	}
	
	@GetMapping("/admin/updateBuffet")
    public String updateBuffetForm(@RequestParam Long buffetId, Model model) {
        System.out.println("L'id del buffet: " + buffetId);
        model.addAttribute("buffet", this.buffetService.searchById(buffetId));
        return "buffetUpdateForm.html";
    }

	
	@PostMapping("/buffetUpdate/{id}")
    public String updateBuffet(@Valid @ModelAttribute("buffet") Buffet buffet, BindingResult bindingResult, Model model) {
        this.buffetValidator.validate(buffet, bindingResult);
        if(!bindingResult.hasErrors()) {
            this.buffetService.inserisci(buffet);
            model.addAttribute("buffet", buffet);
            return "buffet.html";
        }
        else {
            return "buffetUpdateForm.html";
        }
    }
	




}
