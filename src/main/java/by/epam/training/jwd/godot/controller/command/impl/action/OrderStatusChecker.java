package by.epam.training.jwd.godot.controller.command.impl.action;

import by.epam.training.jwd.godot.bean.order_element.Order;
import by.epam.training.jwd.godot.bean.order_element.OrderStatus;
import by.epam.training.jwd.godot.controller.command.Command;
import by.epam.training.jwd.godot.controller.util.messages_provider.MessageProvider;
import by.epam.training.jwd.godot.service.OrderService;
import by.epam.training.jwd.godot.service.ServiceProvider;
import by.epam.training.jwd.godot.service.exception.ServiceException;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

import static by.epam.training.jwd.godot.controller.command.resource.SessionAttr.LOCALE;

public class OrderStatusChecker implements Command {

    private static final Logger LOGGER = Logger.getLogger(OrderStatusChecker.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MessageProvider msgProvider = new MessageProvider(new Locale((String) request.getSession().getAttribute(LOCALE)));

        ServiceProvider provider = ServiceProvider.getInstance();
        OrderService service = provider.getOrderService();

        long orderUid = Long.parseLong(request.getParameter("order_to_check"));

        try {
            Order found = service.getOrder(orderUid);
            String sizesJson = new Gson().toJson(found);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(sizesJson);
            LOGGER.info(sizesJson);
        } catch (ServiceException e) {
            LOGGER.error(e);
            response.getWriter().write(msgProvider.getMessage(e.getClass().getSimpleName()));
        } finally {
            response.getWriter().flush();
            response.getWriter().close();
        }

    }
}
