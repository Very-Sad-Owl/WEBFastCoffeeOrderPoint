package by.epam.training.jwd.godot.controller.command.impl.action;

import by.epam.training.jwd.godot.controller.command.Command;
import by.epam.training.jwd.godot.controller.util.messages_provider.MessageProvider;
import by.epam.training.jwd.godot.service.ServiceProvider;
import by.epam.training.jwd.godot.service.UserService;
import by.epam.training.jwd.godot.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

import static by.epam.training.jwd.godot.controller.command.resource.RequestParam.*;
import static by.epam.training.jwd.godot.controller.command.resource.SessionAttr.LOCALE;

public class UsersManager implements Command {

    private static final Logger LOGGER = Logger.getLogger(UsersManager.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MessageProvider msgProvider = new MessageProvider(new Locale((String) request.getSession().getAttribute(LOCALE)));

        String action = request.getParameter(ACTION);
        ServiceProvider provider = ServiceProvider.getInstance();
        UserService service = provider.getUserService();

        switch (action) {
            case DELETE_ACTION: {
                LOGGER.info("delete\n");
                String toDelete = request.getParameter("login");
                if (toDelete != null) {
                    try {
                        service.deleteUser(toDelete);
                        response.getWriter().print(msgProvider.getMessage(DELETE_ACTION));
                    } catch (ServiceException e) {
                        response.getWriter().print(msgProvider.getMessage(e.getClass().getSimpleName()));
                    }
                }
                break;
            }
            case BAN_ACTION: {
                LOGGER.info("ban\n");
                String toDelete = request.getParameter("login");
                if (toDelete != null) {
                    try {
                        service.banUser(toDelete, true);
                        response.getWriter().print(msgProvider.getMessage(BAN_ACTION));
                    } catch (ServiceException e) {
                        response.getWriter().print(msgProvider.getMessage(e.getClass().getSimpleName()));
                    }
                }
                break;
            }
            case UNBAN_ACTION: {
                LOGGER.info("unban\n");
                String toDelete = request.getParameter("login");
                if (toDelete != null) {
                    try {
                        service.banUser(toDelete, false);
                        response.getWriter().print(msgProvider.getMessage(UNBAN_ACTION));
                    } catch (ServiceException e) {
                        response.getWriter().print(msgProvider.getMessage(e.getClass().getSimpleName()));
                    }
                }
                break;
            }
        }

        response.getWriter().flush();
        response.getWriter().close();
    }
}
