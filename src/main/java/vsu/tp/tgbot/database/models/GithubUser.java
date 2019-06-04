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

    public GithubUser setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public GithubUser setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getName() {
        return name;
    }

    public GithubUser setName(String name) {
        this.name = name;
        return this;
    }

    public Long getIdChatTelegram() {
        return idChatTelegram;
    }

    public GithubUser setIdChatTelegram(Long idChatTelegram) {
        this.idChatTelegram = idChatTelegram;
        return this;
    }

    public String getProfileLink() {
        return profileLink;
    }

    public GithubUser setProfileLink(String profileLink) {
        this.profileLink = profileLink;
        return this;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public GithubUser setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
        return this;
    }

    public Set<Repository> getRepositories() {
        return repositories;
    }

    public GithubUser setRepositories(Set<Repository> repositories) {
        this.repositories = repositories;
        return this;
    }
}