package it.albertus.spring.web;

import it.albertus.spring.model.Utente;
import it.albertus.spring.service.UtenteService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterController {

	@Autowired
	private UtenteService utenteService;

	@RequestMapping(value = { "/register" }, method = RequestMethod.GET)
	public String register() {
		return "register";
	}

	@RequestMapping(value = { "/register" }, method = RequestMethod.POST)
	public String register(Utente utente, BindingResult result, HttpServletRequest request, HttpSession session) {
		String forward;
		try {
			utenteService.save(utente);
			request.setAttribute("messaggio", "Registrazione effettuata!");
			forward = "login";
		}
		catch (Exception e) {
			request.setAttribute("messaggio", "Errore di registrazione: " + e.getLocalizedMessage());
			forward = register();
		}
		return forward;
	}

}