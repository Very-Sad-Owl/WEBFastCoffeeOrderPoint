package by.epam.training.jwd.godot.controller.command.impl.redirect;

import by.epam.training.jwd.godot.bean.IngredientType;
import by.epam.training.jwd.godot.bean.SeasonType;
import by.epam.training.jwd.godot.bean.coffee.Ingredient;
import by.epam.training.jwd.godot.controller.command.Command;
import by.epam.training.jwd.godot.controller.command.resource.CommandUrlPath;
import by.epam.training.jwd.godot.dao.CoffeeDao;
import by.epam.training.jwd.godot.service.CoffeeService;
import by.epam.training.jwd.godot.service.ServiceProvider;
import by.epam.training.jwd.godot.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.epam.training.jwd.godot.controller.command.resource.RequestParam.*;

public class GoToIngredientManagementPage implements Command {

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
            e.printStackTrace();
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(CommandUrlPath.INGREDIENTS_MANAGE_PAGE);
        requestDispatcher.forward(request, response);

    }

}