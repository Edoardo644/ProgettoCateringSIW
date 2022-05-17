package it.uniroma3.siw.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Chef;
import it.uniroma3.siw.model.Piatto;
import it.uniroma3.siw.service.ChefService;


@Component
public class ChefValidator implements Validator {
	
	@Autowired
	ChefService chefService;
	
	@Override
	public void validate(Object o, Errors errors) {
		if(this.chefService.alreadyExists((Chef) o)) {
			errors.reject("chef.duplicato");
		}
	}
	
	@Override
	public boolean supports(Class<?> aClass) {
		return Piatto.class.equals(aClass);
	}

}
