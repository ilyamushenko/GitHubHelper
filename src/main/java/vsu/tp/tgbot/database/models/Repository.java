package vsu.tp.tgbot.database.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "repository")
public class Repository {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long repositoryId;
    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false)
    private String htmlUrl;
    @Column(nullable = false)
    private String pushedAt;
    @Column(nullable = false)
    private String description;
    @ManyToMany(mappedBy = "repositories")
    private Set<GithubUser> subscribers;

    public Repository() {
    }

    public Repository(String fullName, String htmlUrl, String pushedAt, String description, Set<GithubUser> subscribers) {
        this.fullName = fullName;
        this.htmlUrl = htmlUrl;
        this.pushedAt = pushedAt;
        this.description = description;
        this.subscribers = subscribers;
    }

    public Long getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(Long repositoryId) {
        this.repositoryId = repositoryId;
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

    public Set<GithubUser> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(Set<GithubUser> subscribers) {
        this.subscribers = subscribers;
    }
}
