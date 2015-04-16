package it.albertus.spring.web;

import it.albertus.spring.model.Utente;
import it.albertus.spring.service.UtenteService;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterController {

	@Autowired
	private UtenteService utenteService;

	@RequestMapping(value = { "/register" }, method = RequestMethod.GET)
	public String register() {
		return "register"; // Forward!
	}

	@RequestMapping(value = { "/register" }, method = RequestMethod.POST)
	public String register(Utente utente, BindingResult result, HttpServletRequest request, Model model) {
		String forward;
		try {
			utenteService.save(utente);
			model.addAttribute("messaggio", "Registrazione effettuata!");
			forward = "redirect:login?messaggio={messaggio}";
		}
		catch (Exception e) {
			request.setAttribute("messaggio", "Errore di registrazione: " + e.getLocalizedMessage());
			forward = register();
		}
		return forward;
	}

}