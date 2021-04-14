package by.epam.training.jwd.godot.controller.command.impl.action;

import by.epam.training.jwd.godot.bean.coffee.IngredientType;
import by.epam.training.jwd.godot.bean.coffee.SeasonType;
import by.epam.training.jwd.godot.bean.coffee.Ingredient;
import by.epam.training.jwd.godot.controller.command.Command;
import by.epam.training.jwd.godot.controller.util.messages_provider.MessageProvider;
import by.epam.training.jwd.godot.service.CoffeeService;
import by.epam.training.jwd.godot.service.ServiceProvider;
import by.epam.training.jwd.godot.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Locale;

import static by.epam.training.jwd.godot.controller.command.resource.RequestParam.*;
import static by.epam.training.jwd.godot.controller.command.resource.SessionAttr.*;

public class IngredientsManager implements Command {

    private static final Logger LOGGER = Logger.getLogger(IngredientsManager.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MessageProvider msgProvider = new MessageProvider(new Locale((String) request.getSession().getAttribute(LOCALE)));

        String action = request.getParameter(ACTION);
        ServiceProvider provider = ServiceProvider.getInstance();
        CoffeeService service = provider.getCoffeeService();

        if (action.equals(DELETE_ACTION)) {
            String toDelete = request.getParameter(INGREDIENT_TITLE);
            if (toDelete != null) {
                try {
                    service.deleteIngredient(toDelete);
                    response.getWriter().write(msgProvider.getMessage(DELETE_ACTION));
                } catch (ServiceException e) {
                    response.getWriter().print(msgProvider.getMessage(e.getClass().getSimpleName()));
                }
            }
        } else if (action.equals(UPDATE_ACTION)){
            String title = request.getParameter(INGREDIENT_TITLE);
            String iType = request.getParameter(INGREDIENT_TYPE);
            String sType = request.getParameter(INGREDIENT_SEASON);
            double price = Double.parseDouble(request.getParameter(INGREDIENT_PRICE));
            int quantity = Integer.parseInt(request.getParameter(INGREDIENT_QUANTITY));
            String img = request.getParameter(INGREDIENT_IMG);
            String origTitle = request.getParameter(INGREDIENT_PREVIOUS_TITLE);

            try {
                service.updateIngredient(new Ingredient(title, quantity, price,
                        IngredientType.valueOf(iType.toUpperCase()), img,
                        SeasonType.valueOf(sType.toUpperCase())), origTitle);
                response.getWriter().print(msgProvider.getMessage(UPDATE_ACTION));
                LOGGER.info("ingredient update performed");
            } catch (ServiceException e) {
                LOGGER.warn("ingredient hasn't been updated!\n");
                response.getWriter().print(msgProvider.getMessage(e.getClass().getSimpleName()));
            }
        } else if (action.equals(ADD_ACTION)){
            String title = request.getParameter(INGREDIENT_TITLE);
            String iType = request.getParameter(INGREDIENT_TYPE);
            String sType = request.getParameter(INGREDIENT_SEASON);
            double price = Double.parseDouble(request.getParameter(INGREDIENT_PRICE));
            int quantity = Integer.parseInt(request.getParameter(INGREDIENT_QUANTITY));
            String img = request.getParameter(INGREDIENT_IMG);

            try {
                service.addIngredient(new Ingredient(title, quantity, price,
                        IngredientType.valueOf(iType.toUpperCase()), img,
                        SeasonType.valueOf(sType.toUpperCase())));
                response.getWriter().print(msgProvider.getMessage(ADD_ACTION));
                LOGGER.info("ingredient insert performed");
            } catch (ServiceException e) {
                LOGGER.warn("ingredient hasn't been added!\n");
                response.getWriter().print(msgProvider.getMessage(e.getClass().getSimpleName()));
            }
        } else if (action.equals(CHANGE_IMAGE)) {
            LOGGER.info("upload\n");

            String folderPath = "D:/botanstvo/java/godot_coffee_spot/src/main/webapp/resources/image/ingredients/";
            String fileName = "";
            StringBuilder fullPath = new StringBuilder();
            fullPath.append(folderPath);
            for (Part part : request.getParts()) {
                fileName = part.getSubmittedFileName();
                fullPath.append(fileName);
                LOGGER.info(fileName+"\n");
                part.write(fullPath.toString());
            }

            response.getWriter().print("resources/image/ingredients/"+fileName);
        }
        response.getWriter().flush();
        response.getWriter().close();
    }
}
