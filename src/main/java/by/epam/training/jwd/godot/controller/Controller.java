package by.epam.training.jwd.godot.controller;

import by.epam.training.jwd.godot.controller.command.Command;
import by.epam.training.jwd.godot.controller.command.CommandProvider;
import by.epam.training.jwd.godot.controller.command.resource.CommandUrlPath;
import org.apache.log4j.Logger;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(Controller.class);
	
	private final CommandProvider provider = new CommandProvider();

	public Controller() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String callingUrl = request.getHeader("referer");
		if (callingUrl == null){
			callingUrl = CommandUrlPath.GOTOINDEXPAGE;
		}
		String callingCommand = callingUrl.replaceFirst("(.)*/", "");
		LOGGER.info(callingCommand + "\n");
		request.getSession().setAttribute("previousUrl", callingCommand);
		process(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String name;
		Command command;
		
		name = request.getParameter("command");
		LOGGER.info("command = " + name + "\n");
		command = provider.takeCommand(name);

		LOGGER.info(String.format("%s, %s\n", request.getMethod(), name));

		command.execute(request, response);
	}

}
