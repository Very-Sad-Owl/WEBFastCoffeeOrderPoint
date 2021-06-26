package by.epam.training.jwd.godot.controller.util.messages_provider.verification_message_sender;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import static by.epam.training.jwd.godot.controller.util.messages_provider.verification_message_sender.MailHostParameter.*;

import java.util.Properties;

public class EmailSender {

    String SENDER = "olga.mailychko@gmail.com";
    String PASSWORD = "OlyshaMailychko(KIWI)";

    private static final MailHostResourceManager mailManager = new MailHostResourceManager();

    public EmailSender(){}

    public void sendEmail(String userEmail, String userHash) throws MessagingException {

        Properties mailHostProp = new Properties();
        mailHostProp.put(AUTH, mailManager.getValue(AUTH));
        mailHostProp.put(START_TLS, mailManager.getValue(START_TLS));
        mailHostProp.put(HOST, mailManager.getValue(HOST));
        mailHostProp.put(PORT, mailManager.getValue(PORT));

        Session session = Session.getDefaultInstance(mailHostProp, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER, PASSWORD);
            }
        });

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(userEmail));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
        message.setText("Verification link...");
        message.setText(String.format("Controller?command=verify&key1=%s&key2=%s", userEmail, userHash));
        Transport.send(message);
    }

}
