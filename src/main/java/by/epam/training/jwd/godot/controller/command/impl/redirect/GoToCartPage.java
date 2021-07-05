package by.epam.training.jwd.godot.controller.command.impl.redirect;

import by.epam.training.jwd.godot.bean.order_element.Order;
import by.epam.training.jwd.godot.bean.order_element.PaymentMethod;
import by.epam.training.jwd.godot.bean.user.User;
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

import static by.epam.training.jwd.godot.controller.command.resource.CommandUrlPath.GOTOERRORPAGE;
import static by.epam.training.jwd.godot.controller.command.resource.RequestParam.*;

public class GoToCartPage implements Command {

    private static final Logger LOGGER = Logger.getLogger(GoToCartPage.class);

    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServiceProvider provider = ServiceProvider.getInstance();
        OrderService service = provider.getOrderService();
        SpotsService spotsService = provider.getSpotsService();

        try {
            Order cart = service.getCartPositions((User) request.getSession().getAttribute(USER));
            List<PaymentMethod> paymentMethods = Arrays.asList(PaymentMethod.values());
            request.setAttribute("cart", cart);
            request.setAttribute("spots", spotsService.getAll());
            request.setAttribute("payment_methods", paymentMethods);
            LOGGER.info(spotsService.getAll());
            LOGGER.info(cart);
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            response.sendRedirect(GOTOERRORPAGE);
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(CommandUrlPath.CART_PAGE);
        requestDispatcher.forward(request, response);

    }

}
