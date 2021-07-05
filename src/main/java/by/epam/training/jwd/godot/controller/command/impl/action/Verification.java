package by.epam.training.jwd.godot.controller.command.impl.action;

import by.epam.training.jwd.godot.bean.user.User;
import by.epam.training.jwd.godot.controller.command.Command;
import by.epam.training.jwd.godot.service.ServiceProvider;
import by.epam.training.jwd.godot.service.UserService;
import by.epam.training.jwd.godot.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.training.jwd.godot.controller.command.resource.CommandParam.REGISTRATION_SUCCESS;
import static by.epam.training.jwd.godot.controller.command.resource.CommandUrlPath.GOTOINDEXPAGE_WITH_MSG;

public class Verification implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TODO: гений мысли отец русского говнонейминга
        String email = request.getParameter("key1");
        String hash = request.getParameter("key2");

        ServiceProvider provider = ServiceProvider.getInstance();
        UserService service = provider.getUserService();

        try {
            User user = service.retrieveUser(email, hash);
            if (user != null){
                service.activateAccount(user);
            }
            response.sendRedirect(String.format(GOTOINDEXPAGE_WITH_MSG, REGISTRATION_SUCCESS));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

    }
}
