package by.epam.training.jwd.godot.controller.command.impl.action;

import by.epam.training.jwd.godot.bean.coffee.Ingredient;
import by.epam.training.jwd.godot.controller.command.Command;
import by.epam.training.jwd.godot.controller.command.resource.CommandUrlPath;
import by.epam.training.jwd.godot.controller.util.messages_provider.MessageProvider;
import by.epam.training.jwd.godot.service.CoffeeService;
import by.epam.training.jwd.godot.service.ServiceProvider;
import by.epam.training.jwd.godot.service.SpotsService;
import by.epam.training.jwd.godot.service.exception.ServiceException;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

import static by.epam.training.jwd.godot.controller.command.resource.RequestParam.*;
import static by.epam.training.jwd.godot.controller.command.resource.SessionAttr.LOCALE;

public class IngredientsPurchaseManager implements Command {

    private static final Logger LOGGER = Logger.getLogger(IngredientsPurchaseManager.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MessageProvider msgProvider = new MessageProvider(new Locale((String) request.getSession().getAttribute(LOCALE)));

        String action = request.getParameter(ACTION);
        ServiceProvider provider = ServiceProvider.getInstance();
        SpotsService service = provider.getSpotsService();

        String selectedIngredientTitle;
        int amount;
        long spotUid;

        switch (action) {
            case ADD_ACTION:
                selectedIngredientTitle = request.getParameter(INGREDIENT);
                amount = Integer.parseInt(request.getParameter(INGREDIENT_AMOUNT));
                spotUid = Long.parseLong(request.getParameter(SPOT_UID));
                try {
                    service.buyIngredient(spotUid, selectedIngredientTitle, amount);
                    Ingredient added = service.getSpotIngredient(selectedIngredientTitle);
                    String addedItemJson = new Gson().toJson(added);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("utf-8");
                    response.getWriter().write(addedItemJson);
                } catch (ServiceException e) {
                    response.getWriter().write(msgProvider.getMessage(e.getClass().getSimpleName()));
                    LOGGER.error(e.getMessage());
                }
                break;
            case UPDATE_ACTION:
                selectedIngredientTitle = request.getParameter(INGREDIENT);
                amount = Integer.parseInt(request.getParameter(INGREDIENT_AMOUNT));
                spotUid = Long.parseLong(request.getParameter(SPOT_UID));
                try{
                    service.updateIngredientAmount(spotUid, selectedIngredientTitle, amount);
                    response.getWriter().write(msgProvider.getMessage(action));
                } catch (ServiceException e) {
                    response.getWriter().write(msgProvider.getMessage(e.getClass().getSimpleName()));
                    LOGGER.error(e.getMessage());
                }
                break;

        }
        response.getWriter().flush();
        response.getWriter().close();
    }
}
