package vsu.tp.tgbot.response;

public class RegistrationUser {
    private String login;
    private Long idChat;
    private String token;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Long getIdChat() {
        return idChat;
    }

    public void setIdChat(Long idChat) {
        this.idChat = idChat;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
