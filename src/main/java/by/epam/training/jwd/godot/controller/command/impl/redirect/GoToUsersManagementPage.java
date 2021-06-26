package by.epam.training.jwd.godot.controller.command.impl.redirect;

import by.epam.training.jwd.godot.bean.user.User;
import by.epam.training.jwd.godot.controller.command.Command;
import by.epam.training.jwd.godot.controller.command.resource.CommandUrlPath;
import by.epam.training.jwd.godot.service.ServiceProvider;
import by.epam.training.jwd.godot.service.UserService;
import by.epam.training.jwd.godot.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.epam.training.jwd.godot.controller.command.resource.CommandUrlPath.GOTOERRORPAGE;
import static by.epam.training.jwd.godot.controller.command.resource.RequestParam.*;

public class GoToUsersManagementPage implements Command {

    private static final Logger LOGGER = Logger.getLogger(GoToUsersManagementPage.class);

    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServiceProvider provider = ServiceProvider.getInstance();
        UserService service = provider.getUserService();

        try {
            List<User> users = service.getAllUsers();
            request.setAttribute(USERS_LIST, users);
        } catch (ServiceException e) {
            LOGGER.error(e);
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(CommandUrlPath.USERS_MANAGE_PAGE);
        requestDispatcher.forward(request, response);

    }

}
