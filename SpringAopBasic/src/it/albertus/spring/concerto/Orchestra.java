package it.albertus.spring.concerto;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class Orchestra extends Interprete {

	@Override
	public int esegui(String opera) {
		System.out.println(getClass().getSimpleName() + " - Esegue \"" + opera + "\": TATATATAAAAAA - TATATATAAAAAAAAAAAA...");
		if (Math.random() < 0.5D) {
			System.out.println(getClass().getSimpleName() + " - Stonatura!");
			throw new StonaturaException();
		}
		return 30;
	}

}