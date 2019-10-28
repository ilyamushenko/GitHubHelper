package vsu.tp.tgbot.telegrambot;

import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import vsu.tp.tgbot.database.models.GithubUser;
import vsu.tp.tgbot.database.models.Repository;
import vsu.tp.tgbot.database.service.GithubUserService;
import vsu.tp.tgbot.database.service.RepositoryService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Component
@Transactional
public class TelegramBotUtils {

    static GithubUserService githubUserService;
    static RepositoryService repositoryService;
    static GitHub gitHub;
    static boolean firstTime = true;

    public TelegramBotUtils(GithubUserService githubUserService, RepositoryService repositoryService) {
        this.githubUserService = githubUserService;
        this.repositoryService = repositoryService;
    }

    public static List<Repository> getAllSubscribedRepos(Long chatId) {
        GithubUser userByIdChatTelegram = githubUserService.findByIdChatTelegram(chatId);
        return repositoryService.findByUserID(userByIdChatTelegram.getUserId());
    }

    private static GHRepository getGHRepositoryByName(Collection<GHRepository> repositories, String name) {
        return repositories.stream().filter(rep -> name.toLowerCase().equals(rep.getName().toLowerCase())).findFirst().orElse(null);
    }

    public static Map<GHRepository, GHCommit> getLastCommitInfo(Long chatId) {
        if (firstTime) {
            try {
                gitHub = GitHub.connectUsingPassword("ilmamen36@yandex.ru", "nosok1488");
            } catch (IOException e) {
                e.printStackTrace();
            }
            firstTime = false;
        }
        GithubUser userByIdChatTelegram = githubUserService.findByIdChatTelegram(chatId);
        String result = "";
        String token = "token";
        GHCommit lastCommit = null;
        Map<GHRepository, GHCommit> repAndCommit = new HashMap<>();
        //ToDO get all subscribed reps;
        List<Repository> repositories;
        if(userByIdChatTelegram != null) {
            repositories = getAllSubscribedRepos(userByIdChatTelegram.getIdChatTelegram());
        } else {
            repositories = new ArrayList<>();
        }
        try {
            List<GHRepository> needRepositories = new ArrayList<>();
            for (Repository rep : repositories) {
                needRepositories.add(gitHub.getRepository(rep.getFullName()));
            }
            if (!needRepositories.isEmpty()) {

                for (GHRepository repository : needRepositories) {
                    List<GHCommit> ghCommits = repository.listCommits().asList();
                    lastCommit = findLastCommit(ghCommits);
                    repAndCommit.put(repository, lastCommit);
                }

            } else {
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return repAndCommit;
    }

    private static GHCommit findLastCommit(List<GHCommit> commits) {
        return commits.get(0);
    }

    public static String commitToString(GHCommit commit) {
        String result = "Ошибка";
        try {
            result = "Автор коммита: " + commit.getAuthor().getLogin() + ";\n" +
                    "Сообщение: " + commit.getCommitShortInfo().getMessage() + ";\n" +
                    "SHA1: " + commit.getSHA1() + ";\n" +
                    "Строк добавлено: " + commit.getLinesAdded() + ";\n" +
                    "Строк изменено: " + commit.getLinesChanged() + ";\n" +
                    "Строк удалено: " + commit.getLinesDeleted() + ";\n" +
                    "Дата: " + commit.getCommitDate() + ";\n";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String repositoriesToString(List<Repository> repositories) {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < repositories.size(); i++) {
            String pref = String.valueOf(i + 1) + ". ";
            result.append(pref).append(repositories.get(i).getFullName()).append(";\n");
            for(int j = 0; j <= pref.length(); j++) {
                result.append(" ");
            }
            result.append("Ссылка: ").append(repositories.get(i).getHtmlUrl()).append(";\n");
        }
        return result.toString();
    }
}
