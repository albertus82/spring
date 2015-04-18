package it.albertus.spring.web;

import it.albertus.spring.model.Utente;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String home(HttpSession session, ModelMap map) {
		Utente utente = (Utente)session.getAttribute("utente");
		return "home";
	}
}