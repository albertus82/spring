package it.albertus.spring.web;

import it.albertus.spring.model.Utente;
import it.albertus.spring.service.UtenteService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	@Autowired
	private UtenteService utenteService;

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = { "/login" }, method = RequestMethod.POST)
	public String login(Utente utente, HttpServletRequest request, HttpSession session) {
		String forward;
		Utente autenticato = utenteService.auth(utente.getUsername(), utente.getPassword());
		if (autenticato == null) {
			request.setAttribute("messaggio", "Utenza non valida!");
			forward = login();
		}
		else {
			utente = autenticato;
			session.setAttribute("utente", utente);
			forward = "home";
		}
		return forward;
	}

	@RequestMapping(value = { "/logout" }) // , method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	}

}