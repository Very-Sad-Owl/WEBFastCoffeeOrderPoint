package by.epam.training.jwd.godot.controller.command.impl.action;

import by.epam.training.jwd.godot.bean.user.RegistrationInfo;
import by.epam.training.jwd.godot.controller.command.Command;
import by.epam.training.jwd.godot.controller.util.messages_provider.MessageProvider;
import by.epam.training.jwd.godot.service.util.verification_message_sender.EmailSender;
import by.epam.training.jwd.godot.service.exception.*;
import by.epam.training.jwd.godot.service.ServiceProvider;
import by.epam.training.jwd.godot.service.UserService;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Locale;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.epam.training.jwd.godot.controller.command.resource.CommandUrlPath.*;
import static by.epam.training.jwd.godot.controller.command.resource.CommandParam.*;
import static by.epam.training.jwd.godot.controller.command.resource.RequestParam.*;
import static by.epam.training.jwd.godot.controller.command.resource.SessionAttr.LOCALE;

public class Registration implements Command {

	private static final Logger LOGGER = Logger.getLogger(Registration.class);
	private static final EmailSender emailSender = new EmailSender();

	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String login = request.getParameter(LOGIN);
		String password = request.getParameter(PASSWORD);
		String email = request.getParameter(EMAIL);

		ServiceProvider provider = ServiceProvider.getInstance();
		UserService userService = provider.getUserService();
		MessageProvider msgProvider = new MessageProvider(new Locale(
				request.getSession().getAttribute(LOCALE) != null ? (String)request.getSession().getAttribute(LOCALE) : "en")
		);
		String errorMsg = "";

		try {
			String hashPsw = userService.registration(new RegistrationInfo(login, password, email));
			emailSender.sendVerificationLink(email, hashPsw);
			response.sendRedirect(String.format(GOTOINDEXPAGE_WITH_MSG, REGISTRATION_SUCCESS));
		} catch (BannedEmailException | ReservedLoginException e){
			errorMsg = msgProvider.getMessage(e.getClass().getSimpleName());
			response.sendRedirect(String.format(GOTOREGISTRATIONPAGE_WITH_MSG, errorMsg));
			request.setAttribute(MESSAGE, errorMsg);
			LOGGER.info(e);
		} catch (InvalidUserInfoException e){
			StringBuilder fullError = new StringBuilder();
			for (ServiceException cause : e.getCauses()){
				fullError
						.append(msgProvider.getMessage(cause.getClass().getSimpleName()))
						.append(";");
			}
			errorMsg = fullError.toString();
			response.sendRedirect(String.format(GOTOREGISTRATIONPAGE_WITH_MSG, errorMsg));
			LOGGER.info(e);
		} catch (ServiceException e) {
			errorMsg = msgProvider.getMessage(e.getClass().getSimpleName());
			response.sendRedirect(String.format(GOTOREGISTRATIONPAGE_WITH_MSG, errorMsg));
			LOGGER.info(e);
		} catch (MessagingException e) {
			e.printStackTrace(); //TODO: ?? ???? ?????????
		}

	}

}
