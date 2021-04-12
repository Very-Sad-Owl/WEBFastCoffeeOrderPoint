package by.epam.training.jwd.godot.controller.command.impl.redirect;

import by.epam.training.jwd.godot.bean.Order;
import by.epam.training.jwd.godot.bean.PaymentMethod;
import by.epam.training.jwd.godot.bean.User;
import by.epam.training.jwd.godot.bean.coffee.Coffee;
import by.epam.training.jwd.godot.bean.coffee.Ingredient;
import by.epam.training.jwd.godot.controller.command.Command;
import by.epam.training.jwd.godot.controller.command.resource.CommandUrlPath;
import by.epam.training.jwd.godot.service.CoffeeService;
import by.epam.training.jwd.godot.service.OrderService;
import by.epam.training.jwd.godot.service.ServiceProvider;
import by.epam.training.jwd.godot.service.SpotsService;
import by.epam.training.jwd.godot.service.exception.ServiceException;
import com.oracle.wls.shaded.org.apache.xpath.operations.Or;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static by.epam.training.jwd.godot.controller.command.resource.RequestParam.INGREDIENTS;
import static by.epam.training.jwd.godot.controller.command.resource.RequestParam.INGREDIENT_COLUMNS;

public class GoToCartPage implements Command {

    private static final Logger LOGGER = Logger.getLogger(GoToCartPage.class);

    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServiceProvider provider = ServiceProvider.getInstance();
        OrderService service = provider.getOrderService();
        SpotsService spotsService = provider.getSpotsService();

        try {
            Order cart = service.getCartPositions((User) request.getSession().getAttribute("user"));
            List<PaymentMethod> paymentMethods = Arrays.asList(PaymentMethod.values());
            request.setAttribute("cart", cart);
            request.setAttribute("spots", spotsService.getAll());
            request.setAttribute("payment_methods", paymentMethods);
            LOGGER.info(cart);
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace(); // haha classic
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(CommandUrlPath.CART_PAGE);
        requestDispatcher.forward(request, response);

    }

}
