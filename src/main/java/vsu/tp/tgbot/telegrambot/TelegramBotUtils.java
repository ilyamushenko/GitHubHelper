package vsu.tp.tgbot.telegrambot;

import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import vsu.tp.tgbot.database.models.GithubUser;
import vsu.tp.tgbot.database.models.Repository;
import vsu.tp.tgbot.database.service.GithubUserService;
import vsu.tp.tgbot.database.service.RepositoryService;

import java.io.IOException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
@Transactional
public class TelegramBotUtils {

    static GithubUserService githubUserService;
    static RepositoryService repositoryService;

    public TelegramBotUtils(GithubUserService githubUserService, RepositoryService repositoryService) {
        this.githubUserService = githubUserService;
        this.repositoryService = repositoryService;
    }

    public static Set<Repository> getAllSubscribedRepos(Long userChatId) {
        GithubUser userByIdChatTelegram = githubUserService.findByIdChatTelegram(userChatId);
        return userByIdChatTelegram.getRepositories();
    }

    private static GHRepository getGHRepositoryByName(Collection<GHRepository> repositories, String name) {
        return repositories.stream().filter(rep -> name.toLowerCase().equals(rep.getName().toLowerCase())).findFirst().orElse(null);
    }

    public static String getLastCommitInfo(Long chatId, String name) {
        GithubUser userByIdChatTelegram = githubUserService.findByIdChatTelegram(chatId);
        String result = "";
        String token = "token";
        try {
            GitHub gitHub = GitHub.connectUsingOAuth(token);
            Collection<GHRepository> repositoriesFromGitHubApi = gitHub.getMyself().getAllRepositories().values();
            GHRepository needRepository = getGHRepositoryByName(repositoriesFromGitHubApi, name);
            if (needRepository != null) {
                List<GHCommit> ghCommits = needRepository.listCommits().asList();
                GHCommit lastCommit = findLastCommit(ghCommits);

                result += "Автор коммита: " + lastCommit.getAuthor().getLogin() + ";\n" +
                        "Сообщение: " + lastCommit.getCommitShortInfo().getMessage() + ";\n" +
                        "SHA1: " + lastCommit.getSHA1() + ";\n" +
                        "Строк добавлено: " + lastCommit.getLinesAdded() + ";\n" +
                        "Строк изменено: " + lastCommit.getLinesChanged() + ";\n" +
                        "Строк удалено: " + lastCommit.getLinesDeleted() + ";\n" +
                        "Дата: " + lastCommit.getCommitDate() + "\n;";
            } else {
                result = "Нет репозитория с таким именем :(";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private static GHCommit findLastCommit(Collection<GHCommit> commits) {
        Date maxDate = new Date(1);
        GHCommit result = null;
        for(GHCommit commit: commits) {
            try {
                Date commitDate = commit.getCommitDate();
                if(commitDate.compareTo(maxDate) > 0) {
                    maxDate = commitDate;
                    result  = commit;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
