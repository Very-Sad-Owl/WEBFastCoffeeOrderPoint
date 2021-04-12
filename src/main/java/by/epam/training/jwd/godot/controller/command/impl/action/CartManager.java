package by.epam.training.jwd.godot.controller.command.impl.action;

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

import static by.epam.training.jwd.godot.controller.command.resource.RequestParam.*;

public class CartManager implements Command {

    private static final Logger LOGGER = Logger.getLogger(CartManager.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        } catch (ServiceException e) {
            LOGGER.error(e);
        } finally {
            response.getWriter().flush();
            response.getWriter().close();
        }
    }
}
