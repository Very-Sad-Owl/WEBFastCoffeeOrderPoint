package by.epam.training.jwd.godot.controller.util.messages_provider;

import by.epam.training.jwd.godot.controller.command.CommandName;
import by.epam.training.jwd.godot.controller.command.resource.RequestParam;
import by.epam.training.jwd.godot.service.exception.*;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import static by.epam.training.jwd.godot.controller.util.messages_provider.MessagesLocaleNames.*;

public class MessageProvider {
    private Map<String, String> messages = new HashMap<>();
    private static final String BUNDLE_BASE_TITLE = "locale";

        public MessageProvider(Locale locale){
            if (locale == null){
                locale = Locale.getDefault();
            }
            ResourceBundle.clearCache();
            ResourceBundle rb = ResourceBundle.getBundle(BUNDLE_BASE_TITLE, locale);
            messages.put(InvalidLoginException.class.getSimpleName(), rb.getString(INVALID_LOGIN_MSG));
            messages.put(InvalidEmailException.class.getSimpleName(), rb.getString(INVALID_EMAIL_MSG));
            messages.put(InvalidPasswordException.class.getSimpleName(), rb.getString(INVALID_PASSWORD_MSG));
            messages.put(ReservedLoginException.class.getSimpleName(), rb.getString(EXISTING_LOGIN_MSG));
            messages.put(ServiceException.class.getSimpleName(), rb.getString(SERVISE_ERROR_MSG));
            messages.put(NoSuchUserException.class.getSimpleName(), rb.getString(NON_EXISTING_USER_MSG));
            messages.put(InsertionException.class.getSimpleName(), rb.getString(INSERTION_ERROR_MSG));
            messages.put(DeleteException.class.getSimpleName(), rb.getString(DELETE_ERROR_MSG));
            messages.put(UpdateException.class.getSimpleName(), rb.getString(UPDATE_ERROR_MSG));
            messages.put(RequestParam.DELETE_ACTION, rb.getString(DELETE_SUCCESS_MSG));
            messages.put(RequestParam.ADD_ACTION, rb.getString(INSERTION_SUCCESS_MSG));
            messages.put(RequestParam.UPDATE_ACTION, rb.getString(UPDATE_SUCCESS_MSG));
            messages.put(RequestParam.UNBAN_ACTION, rb.getString(UNBAN_SUCCESS_MSG));
            messages.put(RequestParam.BAN_ACTION, rb.getString(BAN_SUCCESS_MSG));
            messages.put(RetrievingException.class.getSimpleName(), RETRIEVING_ERROR);
            messages.put(RequestParam.ORDER_UID, UID_INFO);
            messages.put(BannedEmailException.class.getSimpleName(), EMAIL_BANNED);
    }

    public String getMessage(String cause){
        return messages.get(cause);
    }
}
