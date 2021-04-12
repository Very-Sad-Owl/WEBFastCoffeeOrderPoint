package by.epam.training.jwd.godot.controller.command.impl.redirect;

import by.epam.training.jwd.godot.bean.Spot;
import by.epam.training.jwd.godot.bean.coffee.Ingredient;
import by.epam.training.jwd.godot.controller.command.Command;
import by.epam.training.jwd.godot.controller.command.resource.CommandUrlPath;
import by.epam.training.jwd.godot.service.CoffeeService;
import by.epam.training.jwd.godot.service.ServiceProvider;
import by.epam.training.jwd.godot.service.SpotsService;
import by.epam.training.jwd.godot.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.epam.training.jwd.godot.controller.command.resource.RequestParam.INGREDIENTS;
import static by.epam.training.jwd.godot.controller.command.resource.RequestParam.INGREDIENT_COLUMNS;

public class GoToSpotsManagementPage implements Command {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServiceProvider provider = ServiceProvider.getInstance();
        SpotsService service = provider.getSpotsService();

        try {
            List<Spot> spots = service.getAll();
            request.setAttribute("spots", spots);
        } catch (ServiceException e) {
            e.printStackTrace(); // TODO: so I've chosen death...
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(CommandUrlPath.SPOTS_MANAGE_PAGE);
        requestDispatcher.forward(request, response);

    }

}
