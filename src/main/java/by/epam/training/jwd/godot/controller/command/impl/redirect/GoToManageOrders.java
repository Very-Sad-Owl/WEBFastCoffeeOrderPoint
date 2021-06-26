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

public class GoToManageOrders implements Command {

    private static final Logger LOGGER = Logger.getLogger(GoToManageOrders.class);

    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServiceProvider provider = ServiceProvider.getInstance();
        OrderService service = provider.getOrderService();
        SpotsService spotsService = provider.getSpotsService();

        long spotUid;
        if(request.getParameter(UID) == null || request.getParameter(UID).isEmpty()){
            spotUid = -1;
        } else {
            spotUid = Long.parseLong(request.getParameter(UID));
        }

        try {
            request.setAttribute(UID, spotUid);
            request.setAttribute(ORDER_LIST, service.getInProcessOrders(spotUid));
            request.setAttribute(AVAILABLE_SPOTS, spotsService.getAll());
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(CommandUrlPath.ORDER_MANAGEMENT);
        requestDispatcher.forward(request, response);

    }

}
