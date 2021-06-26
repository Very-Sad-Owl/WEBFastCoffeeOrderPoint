package by.epam.training.jwd.godot.controller.command.impl.action;

import by.epam.training.jwd.godot.bean.order_element.OrderStatus;
import by.epam.training.jwd.godot.controller.command.Command;
import by.epam.training.jwd.godot.controller.command.resource.CommandUrlPath;
import by.epam.training.jwd.godot.controller.util.messages_provider.MessageProvider;
import by.epam.training.jwd.godot.service.OrderService;
import by.epam.training.jwd.godot.service.ServiceProvider;
import by.epam.training.jwd.godot.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

import static by.epam.training.jwd.godot.controller.command.resource.RequestParam.*;
import static by.epam.training.jwd.godot.controller.command.resource.SessionAttr.LOCALE;

public class OrdersManager implements Command {

    private static final Logger LOGGER = Logger.getLogger(OrdersManager.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MessageProvider msgProvider = new MessageProvider(new Locale((String) request.getSession().getAttribute(LOCALE)));

        String action = request.getParameter(ACTION);
        ServiceProvider provider = ServiceProvider.getInstance();
        OrderService service = provider.getOrderService();

        long uid = Long.parseLong(request.getParameter(UID));

        try {
            OrderStatus status = OrderStatus.valueOf(action.toUpperCase());
            service.changeOrderStatus(uid, status);
            response.sendRedirect(CommandUrlPath.ORDER_MANAGEMENT);
        } catch (ServiceException e) {
            response.getWriter().print(msgProvider.getMessage(e.getClass().getSimpleName()));
            LOGGER.error(e);
        } finally {
            response.getWriter().flush();
            response.getWriter().close();
        }
    }
}
