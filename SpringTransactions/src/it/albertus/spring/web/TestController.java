package it.albertus.spring.web;

import it.albertus.spring.service.TestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController {
	
	@Autowired
	private TestService testService;
	
	@RequestMapping(value = { "/test/{method}"}, method = RequestMethod.GET)
	public String home(@PathVariable String method) {
		if ("insert".equals(method))
			testService.insert();
		else if ("rollback".equals(method))
			testService.rollback();
		return "success";
	}
}