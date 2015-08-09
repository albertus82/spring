package it.albertus.spring.web;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.FrameworkServlet;

public class ExchangerServlet extends HttpServlet {

	private static final long serialVersionUID = 5621366128406345488L;

	private InputController inputController;

	@Override
	public void init() throws ServletException {
		ServletContext sc = super.getServletContext();
		String servletName = "dispatcher"; // nome servlet nel web.xml, se presente.
		String attrName = FrameworkServlet.SERVLET_CONTEXT_PREFIX + servletName;
		ApplicationContext servletApplicationContext = WebApplicationContextUtils.getWebApplicationContext(sc, attrName);
		inputController = servletApplicationContext.getBean(InputController.class);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setStatus(HttpStatus.OK.value());
		resp.getWriter().print("<html><head></head><body><h1>" + inputController.toString() + "</h1></body></html>");
	}

}
