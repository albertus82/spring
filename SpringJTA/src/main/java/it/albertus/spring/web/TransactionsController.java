package it.albertus.spring.web;

import it.albertus.spring.service.TransactionTemplateService;
import it.albertus.spring.service.TransactionalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TransactionsController {

	@Autowired
	private TransactionalService transactionalService;

	@Autowired
	private TransactionTemplateService transactionTemplateService;

	@RequestMapping(value = { "/spring/{method}" }, method = RequestMethod.GET)
	public String testTransactionalJdbcOperations(@PathVariable String method) throws Exception {
		if ("insert".equals(method))
			transactionalService.insertJdbcOperations();
		else if ("rollback".equals(method))
			transactionalService.rollbackJdbcOperations();
		return "success";
	}

	@RequestMapping(value = { "/jdbc/{method}" }, method = RequestMethod.GET)
	public String testTransactionalBasicJdbc(@PathVariable String method) {
		if ("insert".equals(method))
			transactionalService.insertBasicJdbc();
		else if ("rollback".equals(method))
			transactionalService.rollbackBasicJdbc();
		return "success";
	}

	@RequestMapping(value = { "/tt" }, method = RequestMethod.GET)
	public String testTransactionTemplate() {
		transactionTemplateService.test(null);
		return "success";
	}

}
