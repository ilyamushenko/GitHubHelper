package vsu.tp.tgbot.database.models;

import org.hibernate.annotations.JoinColumnOrFormula;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "github_user")
public class GithubUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(nullable = false)
    private String login;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Long idChatTelegram;
    @Column(nullable = false)
    private String profileLink;
    @Column(nullable = false)
    private String avatarUrl;
    @ManyToMany
    @JoinTable(
            name = "user_repository",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "repository_id")}
    )
    private Set<Repository> repositories;

    public GithubUser() {}

    public GithubUser(String login, String name, Long idChatTelegram, String profileLink, String avatarUrl, Set<Repository> repositories) {
        this.login = login;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.idChatTelegram = idChatTelegram;
        this.profileLink = profileLink;
        this.repositories = repositories;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getIdChatTelegram() {
        return idChatTelegram;
    }

    public void setIdChatTelegram(Long idChatTelegram) {
        this.idChatTelegram = idChatTelegram;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getProfileLink() {
        return profileLink;
    }

    public void setProfileLink(String profileLink) {
        this.profileLink = profileLink;
    }

    public Set<Repository> getRepositories() {
        return repositories;
    }

    public void setRepositories(Set<Repository> repositories) {
        this.repositories = repositories;
    }
}
