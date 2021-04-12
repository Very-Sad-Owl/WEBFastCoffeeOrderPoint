package by.epam.training.jwd.godot.controller.command.impl.action;

import by.epam.training.jwd.godot.bean.User;
import by.epam.training.jwd.godot.bean.coffee.Coffee;
import by.epam.training.jwd.godot.bean.coffee.CoffeeSize;
import by.epam.training.jwd.godot.bean.coffee.Ingredient;
import by.epam.training.jwd.godot.controller.command.Command;
import by.epam.training.jwd.godot.service.CoffeeService;
import by.epam.training.jwd.godot.service.OrderService;
import by.epam.training.jwd.godot.service.ServiceProvider;
import by.epam.training.jwd.godot.service.exception.ServiceException;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static by.epam.training.jwd.godot.controller.command.resource.SessionAttr.CURRENT_USER;

public class OrderPositionCreator implements Command {

    private static final Logger LOGGER = Logger.getLogger(OrderPositionCreator.class);
    private Coffee chosen;
    private List<Coffee> beverages;
    private List<CoffeeSize> sizes;
    private List<Ingredient> decorators;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceProvider provider = ServiceProvider.getInstance();
        CoffeeService coffeeService = provider.getCoffeeService();
        OrderService orderService = provider.getOrderService();
        beverages = (ArrayList<Coffee>)request.getSession().getAttribute("coffee");

        String action = request.getParameter("action");
        switch (action){
            case "choose":
                try {
                    int posId = request.getParameter("id") != null
                            ? Integer.parseInt(request.getParameter("id")) : 0;
                    chosen = beverages.get(posId);
                    sizes = coffeeService.getSizes(chosen.getType());
                    decorators = coffeeService.getDecorators();

                    String sizesJson = new Gson().toJson(sizes);
                    String decorJson = new Gson().toJson(decorators);
                    String chosenJson = new Gson().toJson(chosen);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");

                    String bothJson = "[" + decorJson + "," + sizesJson + "," + chosenJson + "]";
                    response.getWriter().write(bothJson);
                    break;
                } catch (ServiceException e) {
                    e.printStackTrace(); //TODO: 18+контент!!!!
                }
            case "add":
                try {
                    String[] selectedDecorations = request.getParameterValues("decor");
                    String[] selectedQuantities = request.getParameterValues("decor_amount");
                    String selectedSize = request.getParameter("size");

                    int counter = 0;
                    if(selectedDecorations != null) {
                        for (String el : selectedDecorations) {
                            Ingredient selected = decorators.get(Integer.parseInt(el));
                            int quantity = Integer.parseInt(selectedQuantities[counter]);
                            while (quantity == 0 && counter < selectedQuantities.length){
                                counter++;
                                quantity = Integer.parseInt(selectedQuantities[counter]);
                            }
                            selected.setQuantity(quantity);
                            chosen.addDecoration(selected);
                            counter++;
                        }
                    }
                    chosen.setSize(sizes.get(Integer.parseInt(selectedSize)));
                    orderService.addOrderPosition(chosen, (User)request.getSession().getAttribute(CURRENT_USER));
                    break;
                } catch (ServiceException e) {
                    LOGGER.error(e);
                    e.printStackTrace(); //TODO: here we go again...
                }
        }
        response.getWriter().flush();
        response.getWriter().close();
    }
}
