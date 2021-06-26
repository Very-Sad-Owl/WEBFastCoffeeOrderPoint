package by.epam.training.jwd.godot.controller.util.messages_provider.verification_message_sender;

import by.epam.training.jwd.godot.dao.connection.recource_provider.DBResourceManager;

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
