package vsu.tp.tgbot.database.models;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Set;
@Entity
@Table(name = "repository")
public class Repository {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long repositoryId;
    @Column
    private String fullName;
    @Column
    private String htmlUrl;
    @Column
    private String pushedAt;
    @Column
    private String description;
    @Column
    private String ownerLogin;
    @Column
    private String ownerHtmlUrl;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "repositories")
    private Set<GithubUser> subscribers;

    public Repository() {
    }

    public Repository(String fullName, String htmlUrl, String ownerLogin, String pushedAt, String ownerHtmlUrl, String description, Set<GithubUser> subscribers) {
        this.fullName = fullName;
        this.ownerHtmlUrl = ownerHtmlUrl;
        this.ownerLogin = ownerLogin;
        this.htmlUrl = htmlUrl;
        this.pushedAt = pushedAt;
        this.description = description;
        this.subscribers = subscribers;
    }



    public Long getRepositoryId() {
        return repositoryId;
    }

    public Repository setRepositoryId(Long repositoryId) {
        this.repositoryId = repositoryId;
        return this;
    }

    public String getOwnerLogin() {
        return ownerLogin;
    }

    public Repository setOwnerLogin(String ownerLogin) {
        this.ownerLogin = ownerLogin;
        return this;
    }

    public String getOwnerHtmlUrl() {
        return ownerHtmlUrl;
    }

    public Repository setOwnerHtmlUrl(String ownerHtmlUrl) {
        this.ownerHtmlUrl = ownerHtmlUrl;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public Repository setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public Repository setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
        return this;
    }

    public String getPushedAt() {
        return pushedAt;
    }

    public Repository setPushedAt(String pushedAt) {
        this.pushedAt = pushedAt;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Repository setDescription(String description) {
        this.description = description;
        return this;
    }

    public Set<GithubUser> getSubscribers() {
        return subscribers;
    }

    public Repository setSubscribers(Set<GithubUser> subscribers) {
        this.subscribers = subscribers;
        return this;
    }

    @Override
    public String toString() {
        return "Repository{" +
                "repositoryId=" + repositoryId +
                ", fullName='" + fullName + '\'' +
                ", htmlUrl='" + htmlUrl + '\'' +
                ", pushedAt='" + pushedAt + '\'' +
                ", description='" + description + '\'' +
                ", ownerLogin='" + ownerLogin + '\'' +
                ", ownerHtmlUrl='" + ownerHtmlUrl + '\'' +
                ", subscribers=" + subscribers +
                '}';
    }
}
