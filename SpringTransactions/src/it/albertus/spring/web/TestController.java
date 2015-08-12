package it.albertus.spring.web;

import it.albertus.spring.service.TestService;
import it.albertus.spring.service.TransactionTemplateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController {

	@Autowired
	private TestService testService;

	@Autowired
	private TransactionTemplateService ttService;

	@RequestMapping(value = { "/spring/{method}" }, method = RequestMethod.GET)
	public String testSpring(@PathVariable String method) throws Exception {
		if ("insert".equals(method))
			testService.insertJdbcOperations();
		else if ("rollback".equals(method))
			testService.rollbackJdbcOperations();
		return "success";
	}

	@RequestMapping(value = { "/jdbc/{method}" }, method = RequestMethod.GET)
	public String testJdbc(@PathVariable String method) {
		if ("insert".equals(method))
			testService.insertJdbc();
		else if ("rollback".equals(method))
			testService.rollbackJdbc();
		return "success";
	}

	@RequestMapping(value = { "/tt" }, method = RequestMethod.GET)
	public String testTransactionTemplate() {
		ttService.test(null);
		return "success";
	}

}
