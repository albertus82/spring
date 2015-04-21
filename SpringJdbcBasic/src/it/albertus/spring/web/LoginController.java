package it.albertus.spring.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping(value = { "/login" })
	public String login(HttpServletRequest request, HttpServletResponse response) {
		return "login";
	}
	
	@RequestMapping(value = { "/loginAction" })
	public String loginAction(HttpServletRequest request, HttpServletResponse response) {
		return "error";
	}

}