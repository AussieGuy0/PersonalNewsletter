package me.anthonybruno.personalnewsletter.email;

public interface EmailService {

    void sendEmail(String toAddress, String subject, String body);
}
