package by.epam.training.jwd.godot.controller.command.impl.redirect;

import by.epam.training.jwd.godot.bean.SortingOption;
import by.epam.training.jwd.godot.bean.order_element.Order;
import by.epam.training.jwd.godot.controller.command.Command;
import by.epam.training.jwd.godot.controller.command.resource.CommandUrlPath;
import by.epam.training.jwd.godot.service.OrderService;
import by.epam.training.jwd.godot.service.ServiceProvider;
import by.epam.training.jwd.godot.service.SpotsService;
import by.epam.training.jwd.godot.service.comparator.DateComparatorACS;
import by.epam.training.jwd.godot.service.comparator.DateComparatorDECS;
import by.epam.training.jwd.godot.service.comparator.UidComparator;
import by.epam.training.jwd.godot.service.comparator.UserComparator;
import by.epam.training.jwd.godot.service.exception.ServiceException;
import com.oracle.wls.shaded.org.apache.xpath.operations.Or;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static by.epam.training.jwd.godot.controller.command.resource.CommandUrlPath.GOTOERRORPAGE;
import static by.epam.training.jwd.godot.controller.command.resource.RequestParam.*;

public class GoToOrdersArchivePage implements Command {

    private static final Logger LOGGER = Logger.getLogger(GoToOrdersArchivePage.class);

    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServiceProvider provider = ServiceProvider.getInstance();
        OrderService service = provider.getOrderService();
        int sortParam = request.getParameter(SORTING_OPTION) != null ?
                Integer.parseInt(request.getParameter(SORTING_OPTION)) : 0;
        LOGGER.info(sortParam);

        try {
            List<Order> orders = service.getOrders();
            if(SortingOption.valueOf(sortParam) == SortingOption.BY_USER){
                orders.sort(new UserComparator());
            } else if (SortingOption.valueOf(sortParam) == SortingOption.BY_UID){
                orders.sort(new UidComparator());
            } else if (SortingOption.valueOf(sortParam) == SortingOption.BY_DATE_ASC){
                orders.sort(new DateComparatorACS());
            } else if (SortingOption.valueOf(sortParam) == SortingOption.BY_DATE_DESC){
                orders.sort(new DateComparatorDECS());
            } else {
                orders.sort(new UidComparator());
            }
            LOGGER.info(orders);
            request.setAttribute(ORDER_LIST, orders);
            request.setAttribute(SORTING_OPTION, Arrays.asList(SortingOption.values()));
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(CommandUrlPath.ORDER_ARCHIVE);
        requestDispatcher.forward(request, response);

    }

}
