package it.albertus.spring.web;

import it.albertus.spring.model.Utente;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterController {

	@RequestMapping(value = { "/register" }, method = RequestMethod.GET)
	public String register(Utente utente, Errors errori) {
		return "register"; // Forward!
	}

	@RequestMapping(value = { "/register" }, method = RequestMethod.POST)
	public String register(@Valid Utente utente, Errors errori, BindingResult result, HttpServletRequest request, Model model) {
		String forward;
		if (errori.hasErrors()) {
			model.addAttribute("errori", errori.getFieldErrors());
			return register(utente, errori);
		}
		try {
			model.addAttribute("messaggio", "Registrazione effettuata!");
			forward = "redirect:login?messaggio={messaggio}";
		}
		catch (Exception e) {
			request.setAttribute("messaggio", "Errore di registrazione: " + e.getLocalizedMessage());
			forward = register(utente, errori);
		}
		return forward;
	}

}