package it.albertus.spring.web;

import it.albertus.spring.model.Car;
import it.albertus.spring.service.CarService;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.FrameworkServlet;

@Controller
public class InputController {

	@Autowired
	private CarService carService;

	@RequestMapping(value = "/UserInfo", method = RequestMethod.GET)
	public String showQueryParams(@RequestParam("name") String paramName, @RequestParam(value = "age", defaultValue = "0") short paramAge, Model model) {
		model.addAttribute("nameToShow", paramName.toUpperCase());
		model.addAttribute("ageToShow", paramAge);
		return "userInfo";
	}
	
	@RequestMapping(value = "/cars/{carId}", method = RequestMethod.GET)
	public String showCarInfo(Model model, @PathVariable("carId") long pathVarCarId) {
		Car myCar = carService.findCarById(pathVarCarId);
		model.addAttribute(myCar);
		return "carInfo";
	}

	@RequestMapping(value = "/newcar", method = RequestMethod.GET)
	public String showCarForm() {
		return "carForm";
	}
	
	@RequestMapping(value = "/carForm", method = RequestMethod.GET)
	public void showCarForm2() {
		System.out.println("VOID HANDLER");
	}

	@RequestMapping(value = "/newcar", method = RequestMethod.POST)
	public String registerCar(Car car) {
		car.setMake(car.getMake().toUpperCase());
		/* Controlli ed eventuale salvataggio dell'oggetto */
		return "carInfo";
	}

	@RequestMapping(value = "/testput", method = RequestMethod.GET)
	public String testPutForm(HttpServletRequest request, Model model) {
		System.out.println(request.getServletContext().getFilterRegistrations().toString());
		return "carFormPut";
	}

	@RequestMapping(value = "/testput", method = RequestMethod.PUT)
	public String testPut(Car car, Model model) {
		/* Controlli ed eventuale salvataggio dell'oggetto */
		model.addAttribute("car", car);
		return "carInfo";
	}
	
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String hello(HttpServletResponse response, @CookieValue(value = "hitCounter", defaultValue = "0") Long hitCounter) {
		hitCounter++;
		Cookie cookie = new Cookie("hitCounter", hitCounter.toString());
		response.addCookie(cookie);
		return "welcome";
	}
	
	@RequestMapping(value = "/utils", method = RequestMethod.GET)
	@ResponseBody
	public String test(HttpServletRequest request) {
		ServletContext sc = request.getServletContext();

		String servletName = "dispatcher"; // nome servlet in web.xml, se presente.
		String attrName = FrameworkServlet.SERVLET_CONTEXT_PREFIX + servletName;
		ApplicationContext servletApplicationContext = WebApplicationContextUtils.getWebApplicationContext(sc, attrName);
		InputController inputController = (InputController) servletApplicationContext.getBean("inputController");
		String results = "Bean prelevato dal context della servlet: " + inputController.toString() + ". ";

		ApplicationContext rootApplicationContext = WebApplicationContextUtils.getWebApplicationContext(sc);
		CarService carService = rootApplicationContext.getBean(CarService.class);
		results += "Bean prelevato dal context root: " + carService.toString() + ". ";

		return results;
	}

}
