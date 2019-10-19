package vsu.tp.tgbot.response;

public class SubscribeRepo {
    private String login;
    private String fullName;
    private String htmlUrl;
    private String pushedAt;
    private String description;
    private String ownerLogin;
    private String ownerHtmlUrl;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getPushedAt() {
        return pushedAt;
    }

    public void setPushedAt(String pushedAt) {
        this.pushedAt = pushedAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwnerLogin() {
        return ownerLogin;
    }

    public void setOwnerLogin(String ownerLogin) {
        this.ownerLogin = ownerLogin;
    }

    public String getOwnerHtmlUrl() {
        return ownerHtmlUrl;
    }

    public void setOwnerHtmlUrl(String ownerHtmlUrl) {
        this.ownerHtmlUrl = ownerHtmlUrl;
    }
}
