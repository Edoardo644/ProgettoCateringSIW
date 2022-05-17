package it.uniroma3.siw.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Piatto;
import it.uniroma3.siw.service.PiattoService;


@Component
public class PiattoValidator implements Validator {
	
	@Autowired
	PiattoService piattoService;
	
	@Override
	public void validate(Object o, Errors errors) {
		if(this.piattoService.alreadyExists((Piatto) o)) {
			errors.reject("piatto.duplicato");
		}
	}
	
	@Override
	public boolean supports(Class<?> aClass) {
		return Piatto.class.equals(aClass);
	}


}
