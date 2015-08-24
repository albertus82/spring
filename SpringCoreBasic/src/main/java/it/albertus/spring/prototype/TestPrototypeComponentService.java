package it.albertus.spring.prototype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestPrototypeComponentService {

	@Autowired
	private PrototypeComponent prototypeComponent;

	@Override
	public String toString() {
		return "TestPrototypeComponentServiceImpl [prototypeComponent=" + prototypeComponent + "]";
	}

}
