package it.albertus.spring.web;

import it.albertus.spring.model.Utente;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@Controller
//@SessionAttributes({ "utente" })
public class HomeController {

	@Autowired
	private SessionData sessionData;

	@ModelAttribute
	private SessionData getSessionData() {
		return sessionData;
	}

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

	@RequestMapping(value = { "/{nome}" }, method = RequestMethod.GET)
	public String home(@PathVariable("nome") String name, ModelMap map, HttpServletRequest request) {

		// Esempio di recupero del context dall'interno di un controller...
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		System.out.println(context.toString());

		Utente utente = new Utente();
		utente.setNome(name);
		map.addAttribute("utente", utente);
		sessionData.setCounter(sessionData.getCounter() + 1);
		System.out.println(sessionData.getCounter());

		return "home";
	}

}
