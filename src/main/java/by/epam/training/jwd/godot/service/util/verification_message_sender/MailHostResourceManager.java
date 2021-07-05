package by.epam.training.jwd.godot.service.util.verification_message_sender;

import java.util.ResourceBundle;

public class MailHostResourceManager {
    private final static MailHostResourceManager instance = new MailHostResourceManager();

    private ResourceBundle bundle =
            ResourceBundle.getBundle("mail");

    public static MailHostResourceManager getInstance() {
        return instance;
    }


    public String getValue(String key){
        return bundle.getString(key);
    }
}
