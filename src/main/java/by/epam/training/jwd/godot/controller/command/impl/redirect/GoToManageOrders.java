package by.epam.training.jwd.godot.controller.command.impl.redirect;

import by.epam.training.jwd.godot.bean.Order;
import by.epam.training.jwd.godot.bean.OrderStatus;
import by.epam.training.jwd.godot.bean.PaymentMethod;
import by.epam.training.jwd.godot.bean.User;
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
import java.util.Arrays;
import java.util.List;

public class GoToManageOrders implements Command {

    private static final Logger LOGGER = Logger.getLogger(GoToManageOrders.class);

    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServiceProvider provider = ServiceProvider.getInstance();
        OrderService service = provider.getOrderService();
        SpotsService spotsService = provider.getSpotsService();

        long spotUid;
        if(request.getParameter("uid") != null){
            spotUid = Long.parseLong(request.getParameter("uid"));
        } else {
            spotUid = -1;
        }

        try {
            request.setAttribute("uid", spotUid);
            request.setAttribute("orders", service.getInProcessOrders(spotUid));
            request.setAttribute("spots", spotsService.getAll());

            LOGGER.info("got " + service.getInProcessOrders(spotUid) + "\n");
            LOGGER.info("spotuid  " + spotUid + "\n");
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace(); // haha classic
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(CommandUrlPath.ORDER_MANAGEMENT);
        requestDispatcher.forward(request, response);

    }

}
