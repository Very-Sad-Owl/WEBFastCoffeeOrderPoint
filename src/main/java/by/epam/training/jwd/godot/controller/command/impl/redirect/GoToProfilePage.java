package by.epam.training.jwd.godot.controller.command.impl.redirect;

import by.epam.training.jwd.godot.controller.command.Command;
import by.epam.training.jwd.godot.controller.command.resource.CommandUrlPath;
import by.epam.training.jwd.godot.service.OrderService;
import by.epam.training.jwd.godot.service.ServiceProvider;
import by.epam.training.jwd.godot.service.SpotsService;
import by.epam.training.jwd.godot.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.training.jwd.godot.controller.command.resource.CommandUrlPath.GOTOERRORPAGE;
import static by.epam.training.jwd.godot.controller.command.resource.RequestParam.*;

public class GoToProfilePage implements Command {

    private static final Logger LOGGER = Logger.getLogger(GoToProfilePage.class);

    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServiceProvider provider = ServiceProvider.getInstance();
        OrderService service = provider.getOrderService();

        try {
            request.setAttribute(ORDER_LIST, service.getInProcessOrders(-1));
            LOGGER.info( service.getInProcessOrders(-1) + "\n");
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            response.sendRedirect(GOTOERRORPAGE);
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(CommandUrlPath.PROFILE_PAGE);
        requestDispatcher.forward(request, response);

    }

}
