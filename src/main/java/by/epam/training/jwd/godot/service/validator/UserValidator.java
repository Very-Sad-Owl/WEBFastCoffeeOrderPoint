package by.epam.training.jwd.godot.service.validator;

import by.epam.training.jwd.godot.bean.user.RegistrationInfo;
import by.epam.training.jwd.godot.service.exception.InvalidEmailException;
import by.epam.training.jwd.godot.service.exception.InvalidLoginException;
import by.epam.training.jwd.godot.service.exception.InvalidPasswordException;
import by.epam.training.jwd.godot.service.exception.InvalidUserInfoException;

public class UserValidator {
    private static final String LOGIN_PATTERN = "^.[^\\s\\W]{3,20}";
    private static final String PASSWORD_PATTERN = "^.{6,20}";
    private static final String EMAIL_PATTERN = "[\\w+\\-.]+@[a-z\\d\\-]+(\\.[a-z\\d\\-]+)*\\.[a-z]+";

    private UserValidator(){}

    public static boolean isLoginValid(String login) {
        return login != null && login.matches(LOGIN_PATTERN);
    }

    public static boolean isPasswordValid(String password) {
        return password != null && password.matches(PASSWORD_PATTERN);
    }

    public static boolean isEmailValid(String email){
        return email != null && email.matches(EMAIL_PATTERN);
    }

    public static void validateUserInfo(RegistrationInfo info) throws InvalidUserInfoException {
        InvalidUserInfoException exception = new InvalidUserInfoException();
        if (!isLoginValid(info.getLogin())){
            exception.addCause(new InvalidLoginException("Invalid login"));
        }
        if (!isEmailValid(info.getEmail())){
            exception.addCause(new InvalidEmailException("Invalid email"));
        }
        if (!isPasswordValid(info.getPassword())){
            exception.addCause(new InvalidPasswordException("Invalid password"));
        }

        if(!exception.getCauses().isEmpty()){
            throw exception;
        }
    }
}
