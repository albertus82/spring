package it.albertus.spring.web;

import it.albertus.spring.model.Utente;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	private static final Log log = LogFactory.getLog(HomeController.class);

	@RequestMapping(value = { "/" })
	public String welcome() {
		return "redirect:home"; // Redirect!
	}

	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String home(@AuthenticationPrincipal Utente utente, HttpSession session, ModelMap map) {
		log.info("Utente autenticato: " + utente.getUsername() + " (" + utente.getNome() + ' ' + utente.getCognome() + ')');

		SecurityContext context = SecurityContextHolder.getContext();
		Object principal = context.getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			String username = ((UserDetails) principal).getUsername();
			System.out.println(username);
		}
		else {
			// String username = principal.toString();
		}
		
		session.setAttribute("utente", utente);
		return "home";
	}

}