package it.albertus.spring.concerto;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class Pianista extends Interprete {

	@Override
	public int esegui(String opera) {
		System.out.println(getClass().getSimpleName() + " - Esegue \"" + opera + "\": PLINK PLINK PLINK...");
		return 15 + (int) ((Math.random() - 0.5) * 7);
	}

}
