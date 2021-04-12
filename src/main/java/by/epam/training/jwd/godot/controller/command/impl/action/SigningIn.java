package by.epam.training.jwd.godot.controller.command.impl.action;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.training.jwd.godot.bean.SignInInfo;
import by.epam.training.jwd.godot.bean.User;
import by.epam.training.jwd.godot.bean.UserRole;
import by.epam.training.jwd.godot.controller.command.Command;
import by.epam.training.jwd.godot.controller.util.messages_provider.MessageProvider;
import by.epam.training.jwd.godot.service.exception.NoSuchUserException;
import by.epam.training.jwd.godot.service.exception.ServiceException;
import by.epam.training.jwd.godot.service.ServiceProvider;
import by.epam.training.jwd.godot.service.UserService;

import static by.epam.training.jwd.godot.controller.command.resource.CommandUrlPath.*;
import static by.epam.training.jwd.godot.controller.command.resource.CommandParam.*;
import static by.epam.training.jwd.godot.controller.command.resource.RequestParam.*;
import static by.epam.training.jwd.godot.controller.command.resource.SessionAttr.*;

public class SigningIn implements Command {

	private static final String NONEXISTING_USER_MSG = "locale.nonexisting_password_msg";

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String login;
		String password;

		login = request.getParameter(LOGIN);
		password = request.getParameter(PASSWORD);

		ServiceProvider provider = ServiceProvider.getInstance();
		UserService userService = provider.getUserService();
		MessageProvider msgProvider = new MessageProvider(new Locale((String) request.getSession().getAttribute(LOCALE)));

		User user = null;
		try {
			user = userService.authorization(new SignInInfo(login, password));
			
			if (user == null) {
				response.sendRedirect(String.format(GOTOLOGINPAGE_WITH_MSG,NONEXISTING_USER_MSG));
				return;
			}

			if (user.getRole() == UserRole.ADMIN){
				HttpSession session = request.getSession(true);
				session.setAttribute(AUTHORIZATION, true);
				session.setAttribute(CURRENT_USER, user);
				response.sendRedirect(GOTOADMINPAGE);
			} else if (user.getRole() == UserRole.USER){
				HttpSession session = request.getSession(true);
				session.setAttribute(AUTHORIZATION, true);
				session.setAttribute(CURRENT_USER, user);
				response.sendRedirect(GOTOINDEXPAGE);
			} else {
				HttpSession session = request.getSession(true);
				session.setAttribute(AUTHORIZATION, false);
				response.sendRedirect(GOTOINDEXPAGE);
			}
		} catch (ServiceException e) {
			String errorMsg = msgProvider.getMessage(e.getClass().getSimpleName());
			response.sendRedirect(String.format(GOTOLOGINPAGE_WITH_MSG, errorMsg));
		}

	}

}
