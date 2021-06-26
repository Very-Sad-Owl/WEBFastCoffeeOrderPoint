package by.epam.training.jwd.godot.controller.command.impl.redirect;

import by.epam.training.jwd.godot.bean.coffee.Coffee;
import by.epam.training.jwd.godot.controller.command.Command;
import by.epam.training.jwd.godot.controller.command.resource.CommandUrlPath;
import by.epam.training.jwd.godot.controller.command.resource.RequestParam;
import by.epam.training.jwd.godot.controller.command.resource.SessionAttr;
import by.epam.training.jwd.godot.service.CoffeeService;
import by.epam.training.jwd.godot.service.ServiceProvider;
import by.epam.training.jwd.godot.service.exception.ServiceException;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.epam.training.jwd.godot.controller.command.resource.CommandUrlPath.GOTOERRORPAGE;

public class GoToMainPage implements Command {

	private static final Logger LOGGER = Logger.getLogger(GoToMainPage.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServiceProvider provider = ServiceProvider.getInstance();
		CoffeeService service = provider.getCoffeeService();
		String message = request.getParameter(RequestParam.MESSAGE);
		try {
			List<Coffee> allCoffee = service.getAllCoffee();
			request.setAttribute("coffee", allCoffee);
			request.getSession().setAttribute("coffee", allCoffee);
			LOGGER.info("retrieved: " + allCoffee.size() + "\n");
		} catch (ServiceException e) {
			LOGGER.error(e);
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(CommandUrlPath.MAINPAGE);
		requestDispatcher.forward(request, response);

	}

}
