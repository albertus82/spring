package it.albertus.spring.model;

import org.springframework.stereotype.Component;

//@Component("dragone")
public class UccidiIlDragone implements Missione {
	
	@Override
	public String toString() {
		return "Si parte per il uccidere il dragone!";
	}
}