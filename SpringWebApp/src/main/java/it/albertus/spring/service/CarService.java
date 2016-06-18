package it.albertus.spring.service;

import java.math.BigDecimal;

import it.albertus.spring.model.Car;

import org.springframework.stereotype.Service;

@Service
public class CarService {

	public Car findCarById(Long id) {
		Car car = new Car();
		car.setId(id);
		car.setMake("Ferrari");
		car.setModel("458 Spider");
		car.setPrice(new BigDecimal(205782.0D));
		return car;
	}

}
