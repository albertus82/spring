package it.albertus.spring.service;

import it.albertus.spring.dao.TransactionTemplateDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class TransactionTemplateServiceImpl implements TransactionTemplateService {

	private final TransactionTemplate template;

	@Autowired
	private TransactionTemplateDAO dao;

	@Autowired
	public TransactionTemplateServiceImpl(PlatformTransactionManager txManager) {
		this.template = new TransactionTemplate(txManager);
	}

	public boolean test(final Long param) {
		return template.execute(new TransactionCallback<Boolean>() {
			@Override
			public Boolean doInTransaction(TransactionStatus status) {
				try {
					return dao.test(param);
				}
				catch (IllegalAccessException e) {
					e.printStackTrace();
					status.setRollbackOnly();
					return false;
				}
			}
		});
	}

}
