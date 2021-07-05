package by.epam.training.jwd.godot.service.util.verification_message_sender;

import by.epam.training.jwd.godot.controller.util.messages_provider.MessagesLocaleNames;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import static by.epam.training.jwd.godot.service.util.verification_message_sender.MailHostParameter.*;

import java.util.Properties;
// net blyat' GovnoReceiver
//TODO: наименование говна
public class EmailSender {

    private static final MailHostResourceManager mailManager = new MailHostResourceManager();

    public EmailSender(){}

    private MimeMessage prepareMessage(String userEmail) throws MessagingException {
        Properties mailHostProp = new Properties();
        mailHostProp.put(AUTH, mailManager.getValue(AUTH));
        mailHostProp.put(START_TLS, mailManager.getValue(START_TLS));
        mailHostProp.put(HOST, mailManager.getValue(HOST));
        mailHostProp.put(PORT, mailManager.getValue(PORT));
        String sender = mailManager.getValue(SENDER);

        Session session = Session.getDefaultInstance(mailHostProp, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailManager.getValue(SENDER), mailManager.getValue(PASSWORD));
            }
        });

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(userEmail));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));

        return message;
    }

    public void sendVerificationLink(String userEmail, String userHash) throws MessagingException {
        //TODO: мммм отправлять ссылки со своего основного ящика ммммм
        MimeMessage  message = prepareMessage(userEmail);
        message.setText("Verification link...");
        message.setText(String.format("http://localhost:8080/Controller?command=verify&key1=%s&key2=%s"+
                "&message=" + MessagesLocaleNames.VERIFICATION_SUCCESS,
                userEmail, userHash));
        Transport.send(message);
    }

    public void sendPasswordChangeApprove(String userEmail, String userHash, String userHashConfirmation) throws MessagingException {
        MimeMessage  message = prepareMessage(userEmail);
        message.setText("Here is your link to complete password changing...");
        message.setText(String.format(
                "http://localhost:8080/Controller?command=editprofile&action=allow_psw_update&email=%s&password=%s&password_confirmation=%s",
                userEmail, userHash, userHashConfirmation));
        Transport.send(message);
    }

    public void sendAutogeneratedPassword(String userEmail, String newPsw) throws MessagingException {
        MimeMessage  message = prepareMessage(userEmail);
        message.setText("Here is your new password!\n");
        message.setText(newPsw);
        Transport.send(message);
    }

}
