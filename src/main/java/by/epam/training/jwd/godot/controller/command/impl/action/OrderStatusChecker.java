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

public class OrderStatusChecker implements Command {

    private static final Logger LOGGER = Logger.getLogger(OrderStatusChecker.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceProvider provider = ServiceProvider.getInstance();
        OrderService service = provider.getOrderService();

        long orderUid = Long.parseLong(request.getParameter("order_to_check"));

        try {
            OrderStatus currentStatus = service.getOrderStatus(orderUid);
            response.getWriter().write(currentStatus.toString());
        } catch (ServiceException e) {
            response.getWriter().write("service error");
        }

    }
}
