package vsu.tp.tgbot.database.models;

import org.hibernate.annotations.JoinColumnOrFormula;
import org.springframework.transaction.annotation.Transactional;

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
    @Column
    private String token;
    @Column
    private String name;
    @Column(nullable = false)
    private Long idChatTelegram;
    @Column
    private String profileLink;
    @Column
    private String avatarUrl;
    /*@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_repository",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "repository_id")}
    )
    @Column
    private Set<Repository> repositories;*/

    public GithubUser() {}

    public GithubUser(String login, String token, String name, Long idChatTelegram, String profileLink, String avatarUrl/*, Set<Repository> repositories*/) {
        this.login = login;
        this.token=token;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.idChatTelegram = idChatTelegram;
        this.profileLink = profileLink;
       // this.repositories = repositories;
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

   /* @Transactional
    public Set<Repository> getRepositories() {
        return repositories;
    }

    public GithubUser setRepositories(Set<Repository> repositories) {
        this.repositories = repositories;
        return this;
    }*/

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
