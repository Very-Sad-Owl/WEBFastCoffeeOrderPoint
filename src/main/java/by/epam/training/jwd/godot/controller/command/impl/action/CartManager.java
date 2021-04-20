package by.epam.training.jwd.godot.controller.command.impl.action;

import by.epam.training.jwd.godot.bean.order_element.PaymentMethod;
import by.epam.training.jwd.godot.controller.command.Command;
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
import static by.epam.training.jwd.godot.controller.command.resource.CommandUrlPath.*;
import static by.epam.training.jwd.godot.controller.command.resource.SessionAttr.LOCALE;

public class CartManager implements Command {

    private static final Logger LOGGER = Logger.getLogger(CartManager.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MessageProvider msgProvider = new MessageProvider(new Locale((String) request.getSession().getAttribute(LOCALE)));

        ServiceProvider provider = ServiceProvider.getInstance();
        OrderService service = provider.getOrderService();

        long orderUid = Long.parseLong(request.getParameter(ORDER_UID));
        String[] selectedPositions = request.getParameterValues(SELECTED_POSITIONS);
        String[] selectedAmounts = request.getParameterValues(SELECTED_POSITIONS_AMOUNTS);
        long selectedSpot = Long.parseLong(request.getParameter(AVAILABLE_SPOTS));
        String selectedPaymet = request.getParameter(AVAILABLE_PAYMENT_METHODS);
        int estimatedTime = Integer.parseInt(request.getParameter(ESTIMATED_TIME));
        try {
            service.placeOrder(orderUid, selectedSpot, PaymentMethod.valueOf(selectedPaymet.toUpperCase()),
                    selectedPositions, selectedAmounts, estimatedTime);
            response.getWriter().write(String.format(msgProvider.getMessage(ORDER_UID), orderUid+""));
        } catch (ServiceException e) {
            response.getWriter().print(msgProvider.getMessage(e.getClass().getSimpleName()));
            LOGGER.error(e);
        } finally {
            response.getWriter().flush();
            response.getWriter().close();
        }
    }
}
