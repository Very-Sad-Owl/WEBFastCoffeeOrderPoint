package by.epam.training.jwd.godot.controller.command.impl.action;

import by.epam.training.jwd.godot.bean.OrderStatus;
import by.epam.training.jwd.godot.bean.PaymentMethod;
import by.epam.training.jwd.godot.controller.command.Command;
import by.epam.training.jwd.godot.service.OrderService;
import by.epam.training.jwd.godot.service.ServiceProvider;
import by.epam.training.jwd.godot.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.training.jwd.godot.controller.command.resource.RequestParam.ACTION;

public class OrdersManager implements Command {

    private static final Logger LOGGER = Logger.getLogger(OrdersManager.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter(ACTION);
        ServiceProvider provider = ServiceProvider.getInstance();
        OrderService service = provider.getOrderService();

        long uid = Long.parseLong(request.getParameter("uid"));
        OrderStatus status = OrderStatus.valueOf(action.toUpperCase());

        try {
            service.changeOrderStatus(uid, status);
            response.getWriter().write(status.toString());
        } catch (ServiceException e) {
            e.printStackTrace(); //TODO: aaaaaa
        }
    }
}
