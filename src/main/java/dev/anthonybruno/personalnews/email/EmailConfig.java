package dev.anthonybruno.personalnews.email;

public class EmailConfig {

    private final String username;
    private final String password;

    public EmailConfig(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
