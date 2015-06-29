package it.albertus.spring.web;

import it.albertus.spring.model.Utente;
import it.albertus.spring.service.UtenteService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	@Autowired
	private UtenteService utenteService;

	@RequestMapping(value = { "/" })
	public String welcome() {
		return "redirect:login"; // Redirect!
	}

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String login() {
		return "login"; // Forward!
	}

	@RequestMapping(value = { "/login" }, method = RequestMethod.POST)
	public String login(Utente utente, HttpServletRequest request, HttpSession session, Model model) {
		String forward;
		Utente autenticato = utenteService.auth(utente.getUsername(), utente.getPassword());
		if (autenticato == null) {
			request.setAttribute("messaggio", "Utenza non valida!");
			forward = login();
		}
		else {
			utente = autenticato;
			session.setAttribute("utente", utente);
			forward = "redirect:home/" + utente.getNome();
		}
		return forward;
	}

	@RequestMapping(value = { "/logout" })
	public String logout(HttpSession session, Model model) {
		session.invalidate();
		model.addAttribute("messaggio", "Logout effettuato.");
		return "redirect:login?messaggio={messaggio}";
	}

}