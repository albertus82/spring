package it.albertus.spring.web;

import it.albertus.spring.model.Utente;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	@RequestMapping(value = { "/{nome}/{cognome}" }, method = RequestMethod.GET)
	public String home(@PathVariable("nome") String name, @PathVariable("cognome") String cognome, ModelMap map) {
		
		Utente utente = new Utente();
		utente.setNome(name);
		utente.setCognome(cognome);
		map.addAttribute("utente", utente);

		Set<String> set = new HashSet<String>();
		set.add(utente.getNome());
		set.add(utente.getCognome());
		map.addAttribute("lista", set);
		
		return "home";
	}
	
	@RequestMapping(value = { "/{nome}"}, method = RequestMethod.GET)
	public String home(@PathVariable("nome") String name, ModelMap map) {
//		Utente utente = new Utente();
//		utente.setNome(name);
//		map.addAttribute("utente", utente);
		return "home";
	}
}