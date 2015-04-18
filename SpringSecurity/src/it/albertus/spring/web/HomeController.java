package it.albertus.spring.web;

import it.albertus.spring.model.Utente;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	@RequestMapping(value = { "/" })
	public String welcome() {
		return "redirect:home"; // Redirect!
	}
	
	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String home(HttpSession session, ModelMap map) {
		Utente utente = (Utente)session.getAttribute("utente");
		return "home";
	}
}