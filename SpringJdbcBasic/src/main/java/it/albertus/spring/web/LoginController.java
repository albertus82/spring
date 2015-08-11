package it.albertus.spring.web;

import it.albertus.spring.service.UtenteService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@Autowired
	private UtenteService utenteService;

	@RequestMapping(value = { "/" })
	public String root(HttpServletRequest request, HttpServletResponse response) {
		return login(request, response);
	}

	@RequestMapping(value = { "/login" })
	public String login(HttpServletRequest request, HttpServletResponse response) {
		return "login";
	}

	@RequestMapping(value = { "/auth" })
	public String auth(HttpServletRequest request, HttpServletResponse response) {
		String forward;

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (utenteService.autenticazione(username, password) != null) {
			forward = "success";
		}
		else {
			forward = "error";
		}
		return forward;
	}

}
