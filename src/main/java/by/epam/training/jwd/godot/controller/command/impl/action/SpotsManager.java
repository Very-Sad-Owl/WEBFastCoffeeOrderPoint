package by.epam.training.jwd.godot.controller.command.impl.action;

import by.epam.training.jwd.godot.controller.command.Command;
import by.epam.training.jwd.godot.controller.util.messages_provider.MessageProvider;
import by.epam.training.jwd.godot.service.ServiceProvider;
import by.epam.training.jwd.godot.service.SpotsService;
import by.epam.training.jwd.godot.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

import static by.epam.training.jwd.godot.controller.command.resource.RequestParam.*;
import static by.epam.training.jwd.godot.controller.command.resource.SessionAttr.LOCALE;

public class SpotsManager implements Command {

    private static final Logger LOGGER = Logger.getLogger(SpotsManager.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MessageProvider msgProvider = new MessageProvider(new Locale((String) request.getSession().getAttribute(LOCALE)));

        String action = request.getParameter(ACTION);
        ServiceProvider provider = ServiceProvider.getInstance();
        SpotsService service = provider.getSpotsService();

        switch (action) {
            case DELETE_ACTION:
                String toDelete = request.getParameter(UID);
                if (toDelete != null) {
                    try {
                        service.deleteSpot(Integer.parseInt(toDelete));
                    } catch (ServiceException e) {
                        response.getWriter().print(msgProvider.getMessage(e.getClass().getSimpleName()));
                    }
                }
                break;
            case UPDATE_ACTION: {
                long uid = Long.parseLong(request.getParameter(UID));
                String region = request.getParameter(REGION);
                String city = request.getParameter(CITY);
                String street = request.getParameter(STREET);
                String house = request.getParameter(HOUSE);

                String regionRu = request.getParameter(REGION_RU);
                String cityRu = request.getParameter(CITY_RU);
                String streetRu = request.getParameter(STREET_RU);

                try {
                    service.updateSpot(region, regionRu, city, cityRu, street, streetRu, house, uid);
                } catch (ServiceException e) {
                    response.getWriter().print(msgProvider.getMessage(e.getClass().getSimpleName()));
                    LOGGER.warn("spot address hasn't been updated!\n");
                }
                break;
            }
            case ADD_ACTION: {
                String region = request.getParameter(REGION);
                String city = request.getParameter(CITY);
                String street = request.getParameter(STREET);
                String house = request.getParameter(HOUSE);

                String regionRu = request.getParameter(REGION_RU);
                String cityRu = request.getParameter(CITY_RU);
                String streetRu = request.getParameter(STREET_RU);

                try {
                    service.addSpot(region, regionRu, city, cityRu, street, streetRu, house);
                } catch (ServiceException e) {
                    response.getWriter().print(msgProvider.getMessage(e.getClass().getSimpleName()));
                    LOGGER.warn("spot address hasn't been added!\n");
                }
                break;
            }
        }
        response.getWriter().flush();
        response.getWriter().close();
    }
}
