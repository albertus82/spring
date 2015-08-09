package it.albertus.spring.web;

import it.albertus.spring.model.Utente;
import it.albertus.spring.model.validation.UtenteChecks;
import it.albertus.spring.validator.UtenteValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterController {

	@Autowired
	private UtenteValidator springValidator;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields("username", "password", "nome", "cognome", "dataNascita");
	}

	@RequestMapping(value = { "/register" }, method = RequestMethod.GET)
	public String register(Utente utente) {
		return "register"; // Forward!
	}

	@RequestMapping(value = { "/register" }, method = RequestMethod.POST)
	public String register(@Validated(UtenteChecks.class) Utente utente, BindingResult errors, Model model) {
		final String forward;
		// springValidator.validate(utente, errors);
		if (errors.hasErrors()) {
			forward = "register";
		}
		else {
			// utenteService.save(utente);
			model.addAttribute("messaggio", "Registrazione effettuata!");
			forward = "redirect:login?messaggio={messaggio}";
		}
		return forward;
	}

}
