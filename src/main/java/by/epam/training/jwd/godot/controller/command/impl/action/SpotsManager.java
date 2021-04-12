package by.epam.training.jwd.godot.controller.command.impl.action;

import by.epam.training.jwd.godot.bean.IngredientType;
import by.epam.training.jwd.godot.bean.SeasonType;
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
import javax.servlet.http.Part;
import java.io.IOException;

import static by.epam.training.jwd.godot.controller.command.resource.RequestParam.*;

public class SpotsManager implements Command {

    private static final Logger LOGGER = Logger.getLogger(SpotsManager.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter(ACTION);
        ServiceProvider provider = ServiceProvider.getInstance();
        SpotsService service = provider.getSpotsService();

        switch (action) {
            case DELETE_ACTION:
                String toDelete = request.getParameter("uid");
                if (toDelete != null) {
                    try {
                        service.deleteSpot(Integer.parseInt(toDelete));
                    } catch (ServiceException e) {
                        response.getWriter().print("No data was deleted due to db error");
                    }
                }
                //response.sendRedirect("Controller?command=gotomanageingredientspage");
                break;
            case UPDATE_ACTION: {
                long uid = Long.parseLong(request.getParameter("uid"));
                String region = request.getParameter("region");
                String city = request.getParameter("city");
                String street = request.getParameter("street");
                String house = request.getParameter("house");

                String regionRu = request.getParameter("region_ru");
                String cityRu = request.getParameter("city_ru");
                String streetRu = request.getParameter("street_ru");

                try {
                    service.updateSpot(region, regionRu, city, cityRu, street, streetRu, house, uid);
                } catch (ServiceException e) {
                    LOGGER.warn("spot address hasn't been updated!\n");
                }
                break;
            }
            case ADD_ACTION: {
                String region = request.getParameter("region");
                String city = request.getParameter("city");
                String street = request.getParameter("street");
                String house = request.getParameter("house");

                String regionRu = request.getParameter("region_ru");
                String cityRu = request.getParameter("city_ru");
                String streetRu = request.getParameter("street_ru");

                try {
                    service.addSpot(region, regionRu, city, cityRu, street, streetRu, house);
                } catch (ServiceException e) {
                    LOGGER.warn("spot address hasn't been added!\n");
                }
                break;
            }
        }
        response.getWriter().flush();
        response.getWriter().close();
    }
}
