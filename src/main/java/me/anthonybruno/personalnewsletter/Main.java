package me.anthonybruno.personalnewsletter;

import me.anthonybruno.personalnewsletter.email.EmailConfig;
import me.anthonybruno.personalnewsletter.email.EmailService;
import me.anthonybruno.personalnewsletter.email.Gmail;

import java.io.File;

public class Main {

    private static final File secretsFolder = new ClassPathFile("secret").getFile();

    public static void main(String[] args) {
        PropertiesFileReader props = new PropertiesFileReader(new File(secretsFolder, "email.properties"));

        EmailConfig emailConfig = new EmailConfig(props.getProperty("username"), props.getProperty("password"));
        EmailService emailService = new Gmail(emailConfig);
        emailService.sendEmail(emailConfig.getUsername() + "@gmail.com", "Hello World", "Hi there!");
    }
}
