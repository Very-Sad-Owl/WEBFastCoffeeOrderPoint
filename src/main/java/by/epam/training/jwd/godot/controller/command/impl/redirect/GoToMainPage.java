package by.epam.training.jwd.godot.controller.command.impl.redirect;

import by.epam.training.jwd.godot.bean.coffee.Coffee;
import by.epam.training.jwd.godot.bean.user.User;
import by.epam.training.jwd.godot.bean.user.UserRole;
import by.epam.training.jwd.godot.controller.command.Command;
import by.epam.training.jwd.godot.controller.command.resource.CommandUrlPath;
import by.epam.training.jwd.godot.controller.command.resource.RequestParam;
import by.epam.training.jwd.godot.controller.command.resource.SessionAttr;
import by.epam.training.jwd.godot.service.CoffeeService;
import by.epam.training.jwd.godot.service.ServiceProvider;
import by.epam.training.jwd.godot.service.exception.ServiceException;
import org.apache.log4j.Logger;
import sun.security.x509.AVA;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.epam.training.jwd.godot.controller.command.resource.CommandUrlPath.GOTOADMINPAGE;
import static by.epam.training.jwd.godot.controller.command.resource.RequestParam.*;
import static by.epam.training.jwd.godot.controller.command.resource.SessionAttr.CURRENT_USER;

public class GoToMainPage implements Command {

	private static final Logger LOGGER = Logger.getLogger(GoToMainPage.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServiceProvider provider = ServiceProvider.getInstance();
		CoffeeService service = provider.getCoffeeService();
		String to = CommandUrlPath.MAINPAGE;
		User user = (User)request.getSession().getAttribute(CURRENT_USER);
		if (user  != null && user.getRole().getId() == UserRole.ADMIN.getId()){
			to = CommandUrlPath.ADMIN_PAGE;
		}
		try {
			List<Coffee> allCoffee = service.getAllCoffee();
			request.setAttribute(AVAILABLE_BEVERAGES, allCoffee);
			request.getSession().setAttribute(AVAILABLE_BEVERAGES, allCoffee);
			LOGGER.info("retrieved: " + allCoffee.size() + "\n");
		} catch (ServiceException e) {
			LOGGER.error(e);
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(to);
		requestDispatcher.forward(request, response);

	}

}
