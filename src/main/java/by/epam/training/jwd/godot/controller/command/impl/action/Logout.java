package by.epam.training.jwd.godot.controller.command.impl.action;

import by.epam.training.jwd.godot.controller.command.Command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static by.epam.training.jwd.godot.controller.command.resource.CommandUrlPath.*;
import static by.epam.training.jwd.godot.controller.command.resource.CommandParam.*;
import static by.epam.training.jwd.godot.controller.command.resource.SessionAttr.*;

public class Logout implements Command {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		HttpSession session = request.getSession();
		
		if(session != null) {
			session.removeAttribute(AUTHORIZATION);
		}
		response.sendRedirect(String.format(GOTOINDEXPAGE_WITH_MSG, LOGOUT));

	}

}
