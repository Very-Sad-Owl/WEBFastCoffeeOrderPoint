package by.epam.training.jwd.godot.controller.command.impl.action;

import by.epam.training.jwd.godot.bean.user.User;
import by.epam.training.jwd.godot.controller.command.Command;
import by.epam.training.jwd.godot.controller.util.messages_provider.MessageProvider;
import by.epam.training.jwd.godot.service.ServiceProvider;
import by.epam.training.jwd.godot.service.UserService;
import by.epam.training.jwd.godot.service.exception.ServiceException;
import by.epam.training.jwd.godot.service.util.ImageSaver;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

import static by.epam.training.jwd.godot.controller.command.resource.RequestParam.*;
import static by.epam.training.jwd.godot.controller.command.resource.SessionAttr.LOCALE;

public class ProfileAction implements Command {

    private static final Logger LOGGER = Logger.getLogger(ProfileAction.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MessageProvider msgProvider = new MessageProvider(new Locale((String) request.getSession().getAttribute(LOCALE)));

        String action = request.getParameter(ACTION);
        ServiceProvider provider = ServiceProvider.getInstance();
        UserService service = provider.getUserService();

        switch (action){
            case CHANGE_IMAGE:
                LOGGER.info("upload\n");
                try {
                    String folderPath = "D:/botanstvo/java/godot_coffee_spot/src/main/webapp/resources/image/profile_img/";
                    ImageSaver saver = new ImageSaver();
                    String relPath = "resources/image/profile_img/" + saver.upload(request.getParts(), folderPath);

                    User current = (User)request.getSession().getAttribute("user");
                    service.changeAvatar(current.getLogin(), relPath);
                    request.getSession().setAttribute("user", service.retrieveUser(current.getLogin()));

                    response.getWriter().print(relPath);
                } catch (ServiceException e) {
                    response.getWriter().print(msgProvider.getMessage(e.getClass().getSimpleName()));
                    e.printStackTrace(); //TODO: убрать эту порнографию
                }
                break;
            case UPDATE_ACTION:
                String login = request.getParameter(USER_LOGIN);
                String password = request.getParameter(USER_PASSWORD);
                String email = request.getParameter(USER_EMAIL);

                try {
                    service.changeUserContacts(login, email, password);
                    request.getSession().setAttribute("user", service.retrieveUser(login));
                    response.getWriter().print(msgProvider.getMessage(UPDATE_ACTION));
                } catch (ServiceException e) {
                    response.getWriter().print(msgProvider.getMessage(e.getClass().getSimpleName()));
                    e.printStackTrace(); //TODO: эту тоже
                }
                break;
        }
        response.getWriter().flush();
        response.getWriter().close();
    }
}
