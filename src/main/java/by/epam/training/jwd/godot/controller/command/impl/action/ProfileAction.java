package by.epam.training.jwd.godot.controller.command.impl.action;

import by.epam.training.jwd.godot.bean.user.User;
import by.epam.training.jwd.godot.controller.command.Command;
import by.epam.training.jwd.godot.controller.recource_provider.FolderResourceManager;
import by.epam.training.jwd.godot.controller.util.messages_provider.MessageProvider;
import by.epam.training.jwd.godot.service.ServiceProvider;
import by.epam.training.jwd.godot.service.UserService;
import by.epam.training.jwd.godot.service.exception.ServiceException;
import by.epam.training.jwd.godot.service.util.ImageSaver;
import org.apache.log4j.Logger;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

import static by.epam.training.jwd.godot.controller.command.resource.CommandUrlPath.GOTOINDEXPAGE_WITH_MSG;
import static by.epam.training.jwd.godot.controller.command.resource.CommandUrlPath.GOTOLOGINPAGE_WITH_MSG;
import static by.epam.training.jwd.godot.controller.command.resource.RequestParam.*;
import static by.epam.training.jwd.godot.controller.command.resource.SessionAttr.CURRENT_USER;
import static by.epam.training.jwd.godot.controller.command.resource.SessionAttr.LOCALE;
import static by.epam.training.jwd.godot.controller.recource_provider.FolderParameter.*;

public class ProfileAction implements Command {

    private static final Logger LOGGER = Logger.getLogger(ProfileAction.class);
    private static final FolderResourceManager resourceManager = FolderResourceManager.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MessageProvider msgProvider = new MessageProvider(request.getSession().getAttribute(LOCALE) != null ?
                new Locale((String) request.getSession().getAttribute(LOCALE)) : Locale.ENGLISH);

        String action = request.getParameter(ACTION);
        ServiceProvider provider = ServiceProvider.getInstance();
        UserService service = provider.getUserService();

        String email = request.getParameter(EMAIL);
        String newPsw = request.getParameter(PASSWORD);
        String checkPsw = request.getParameter(PASSWORD_CONFIRMATION);

        switch (action){
            case CHANGE_IMAGE:
                try {
                    String folderPath = resourceManager.getValue(USER_IMG_FULL_PATH);
                    ImageSaver saver = new ImageSaver();
                    String relPath = resourceManager.getValue(USER_IMG_REL_PATH)
                            + saver.upload(request.getParts(), folderPath);

                    User current = (User)request.getSession().getAttribute(CURRENT_USER);
                    service.changeAvatar(current.getLogin(), relPath);
                    request.getSession().setAttribute(USER, service.retrieveUser(current.getLogin()));

                    response.getWriter().print(relPath);
                } catch (ServiceException e) {
                    response.getWriter().print(msgProvider.getMessage(e.getClass().getSimpleName()));
                    LOGGER.error(e);
                }
                break;
            case SEND_PSW_UPDATE_MSG:
                try {
                    service.requestPasswordUpdateConfirmation(email, newPsw, checkPsw);
                    response.getWriter().print(msgProvider.getMessage(SEND_PSW_UPDATE_MSG));
                } catch (ServiceException e) {
                    response.getWriter().print(msgProvider.getMessage(e.getClass().getSimpleName()));
                    LOGGER.error(e);
                }
                break;
            case RESET_PASSWORD:
                try {
                    service.resetUserPassword(email);
                    response.sendRedirect(String.format(GOTOINDEXPAGE_WITH_MSG, msgProvider.getMessage(SEND_PSW_UPDATE_MSG)));
                } catch (ServiceException e) {
                    response.sendRedirect(String.format(GOTOLOGINPAGE_WITH_MSG, msgProvider.getMessage(e.getClass().getSimpleName())));
                    LOGGER.error(e);
                }
                break;
            case APPLY_PASSWORD_UPDATE:
                try {
                    service.updateUserPassword(email, newPsw, checkPsw);
                    request.getSession().setAttribute(USER, service.retrieveUser(email));
//                    response.getWriter().print(msgProvider.getMessage(UPDATE_ACTION));
                    response.sendRedirect(String.format(GOTOINDEXPAGE_WITH_MSG, msgProvider.getMessage(UPDATE_ACTION)));
                } catch (ServiceException e) {
                    response.getWriter().print(msgProvider.getMessage(e.getClass().getSimpleName()));
                    LOGGER.error(e);
                }
                break;

        }
        response.getWriter().flush();
        response.getWriter().close();
    }
}
