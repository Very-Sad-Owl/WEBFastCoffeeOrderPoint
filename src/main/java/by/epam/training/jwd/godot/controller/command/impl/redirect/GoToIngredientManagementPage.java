package by.epam.training.jwd.godot.controller.command.impl.redirect;

import by.epam.training.jwd.godot.bean.coffee.IngredientType;
import by.epam.training.jwd.godot.bean.coffee.SeasonType;
import by.epam.training.jwd.godot.bean.coffee.Ingredient;
import by.epam.training.jwd.godot.controller.command.Command;
import by.epam.training.jwd.godot.controller.command.resource.CommandUrlPath;
import by.epam.training.jwd.godot.service.CoffeeService;
import by.epam.training.jwd.godot.service.ServiceProvider;
import by.epam.training.jwd.godot.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.epam.training.jwd.godot.controller.command.resource.CommandUrlPath.GOTOERRORPAGE;
import static by.epam.training.jwd.godot.controller.command.resource.RequestParam.*;

public class GoToIngredientManagementPage implements Command {

    private static final Logger LOGGER = Logger.getLogger(GoToIngredientManagementPage.class);

    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServiceProvider provider = ServiceProvider.getInstance();
        CoffeeService service = provider.getCoffeeService();

        try {
            List<Ingredient> ingredients = service.getAllIngredients();
            List<String> columns = service.getIngredientColumns();
            request.setAttribute(INGREDIENTS, ingredients);
            request.setAttribute(INGREDIENT_COLUMNS, columns);
            request.setAttribute("ingredient_types", IngredientType.values());
            request.setAttribute("season_types", SeasonType.values());
        } catch (ServiceException e) {
            LOGGER.error(e);
            response.sendRedirect(GOTOERRORPAGE);
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(CommandUrlPath.INGREDIENTS_MANAGE_PAGE);
        requestDispatcher.forward(request, response);

    }

}