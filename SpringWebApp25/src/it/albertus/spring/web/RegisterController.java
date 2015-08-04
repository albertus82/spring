package it.albertus.spring.web;

import it.albertus.spring.model.Utente;
import it.albertus.spring.service.UtenteService;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterController {
	
	@Autowired
	private UtenteService utenteService;
	
	@ModelAttribute("utente")
	private Utente createUtente() {
		Utente utente = new Utente();
		utente.setDataNascita(new Date());
		return utente;
	}
	
	@ExceptionHandler(Exception.class)
	public String handleException(Exception e, HttpServletRequest request) {
		return "redirect:error?page=" + this.getClass().getSimpleName();
	}
	
	@RequestMapping(value = { "/register" }, method = RequestMethod.GET)
	public String register(Utente utente, Errors errori) {
		return "register"; // Forward!
	}

	@RequestMapping(value = { "/register" }, method = RequestMethod.POST)
	public String register(@Valid Utente utente, Errors errori, BindingResult result, HttpServletRequest request, Model model) throws Exception {
		String forward;
		if (errori.hasErrors()) {
			model.addAttribute("errori", errori.getFieldErrors());
			return register(utente, errori);
		}
		try {
			utenteService.save(utente);
			model.addAttribute("messaggio", "Registrazione effettuata!");
			forward = "redirect:login?messaggio={messaggio}";
		}
		catch (Exception e) {
			throw e;
//			request.setAttribute("messaggio", "Errore di registrazione: " + e.getLocalizedMessage());
//			forward = register(utente, errori);
		}
		return forward;
	}

}