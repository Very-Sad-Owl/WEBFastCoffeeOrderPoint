package by.epam.training.jwd.godot.service.util;

import org.apache.commons.lang.RandomStringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHasher {

    public PasswordHasher(){}

    public String hashPassword(String passwordToHash) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(passwordToHash.getBytes());
        byte[] bytes = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    public String generatePassword() throws NoSuchAlgorithmException {
        String generatedString = RandomStringUtils.randomAlphanumeric(6);
        return hashPassword(generatedString);
    }
}
