package it.albertus.spring.web;

import it.albertus.spring.service.UtenteService;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@Autowired
	private UtenteService utenteService;

	@RequestMapping(value = { "/customlogin" })
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public String login() {
		return "customlogin"; // Forward!
	}

	@RequestMapping(value = { "/logout" })
	public String logout(HttpSession session, Model model) {
		session.invalidate();
		return "redirect:home";
	}

}