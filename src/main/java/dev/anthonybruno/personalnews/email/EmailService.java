package dev.anthonybruno.personalnews.email;

public interface EmailService {

    void sendEmail(String toAddress, String subject, String body);
}
