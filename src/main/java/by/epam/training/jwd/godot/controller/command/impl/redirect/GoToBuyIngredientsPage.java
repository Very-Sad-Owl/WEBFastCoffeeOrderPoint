package by.epam.training.jwd.godot.controller.command.impl.redirect;

import by.epam.training.jwd.godot.bean.coffee.Ingredient;
import by.epam.training.jwd.godot.controller.command.Command;
import by.epam.training.jwd.godot.controller.command.resource.CommandUrlPath;
import by.epam.training.jwd.godot.service.CoffeeService;
import by.epam.training.jwd.godot.service.ServiceProvider;
import by.epam.training.jwd.godot.service.SpotsService;
import by.epam.training.jwd.godot.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.epam.training.jwd.godot.controller.command.resource.CommandUrlPath.GOTOERRORPAGE;

public class GoToBuyIngredientsPage implements Command {

    private static final Logger LOGGER = Logger.getLogger(GoToBuyIngredientsPage.class);

    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String uid = request.getParameter("uid");
        LOGGER.info("uid " + uid + "\n");
        ServiceProvider provider = ServiceProvider.getInstance();
        SpotsService service = provider.getSpotsService();
        CoffeeService coffeeService = provider.getCoffeeService();
        try {
            List<Ingredient> ingredients = service.getSpotIngredients(Integer.parseInt(uid));
            List<Ingredient> allIngredients = coffeeService.getAllIngredients();
            request.setAttribute("ingredients", ingredients);
            request.setAttribute("all_ingredients", allIngredients);
            request.setAttribute("uid", uid);
        } catch (ServiceException e) {
            LOGGER.error(e);
            response.sendRedirect(GOTOERRORPAGE);
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(CommandUrlPath.BUY_INGREDINTS);
        requestDispatcher.forward(request, response);

    }

}
