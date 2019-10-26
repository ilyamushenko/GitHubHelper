package vsu.tp.tgbot.controllers;


import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHRepositorySearchBuilder;
import org.kohsuke.github.GitHub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vsu.tp.tgbot.Utils.QueueChatIds;
import vsu.tp.tgbot.database.models.GithubUser;
import vsu.tp.tgbot.database.models.Repository;
import vsu.tp.tgbot.database.service.GithubUserService;
import vsu.tp.tgbot.database.service.RepositoryService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {
/*
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private  GithubUserService githubUserService;
    @Autowired
    private  RepositoryService repositoryService;

        @Autowired
        public UserController(GithubUserService githubUserService, RepositoryService repositoryService) {
            this.githubUserService = githubUserService;
            this.repositoryService = repositoryService;
        }

    @GetMapping("/test")
    public Object tryToLoginGithub() {
        String login = "ilmamen36@yandex.ru";
        String password = "1q2w3e4r5t6y";
        GitHub gitHub;
        GHRepositorySearchBuilder task = null;

        try {
            logger.info("Connecting: ilmamen36@yandex.ru");
            gitHub = GitHub.connectUsingOAuth("1aaae51f46631ed27508c2f260118d3f50ef5880");
            boolean isValid = gitHub.isCredentialValid();
            if (isValid) {
                logger.info("Success!");
                task = gitHub.searchRepositories().q("mamki");
                logger.info("sdaasdasda");

                return task.list().asList();
//                PagedIterator<GHRepository> iterator = listOfRepositories.iterator();
//                List<java.net.URL> temp = new ArrayList<>();
//                iterator.forEachRemaining(rep -> temp.add(rep.getHtmlUrl()));
//                return temp;
//                //GHRepository repository = gitHub.getRepository("Task");
            } else {
                logger.info("Login Error!");
                //return "Увы(";
            }

        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return new ArrayList();
    }

    //@PostMapping
//    @GetMapping
//    public Object returnUser() {
//        //String token = authToken.get("token");
//        try {
//            GitHub gitHub = GitHub.connectUsingOAuth("1aaae51f46631ed27508c2f260118d3f50ef5880");
//            gitHub.getMyself().;
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
    //ToDO: заменить токен на ghToken
    @PostMapping("/check")
    public GithubUser checkUser(Map<String, String> tokenAndChatId) {
        Long    chatId = Long.valueOf(tokenAndChatId.get("chatId"));
        String ghToken = tokenAndChatId.get("token");
        GithubUser githubUser = null;
        try {
            if(QueueChatIds.check(chatId)) {
                GitHub gitHub = GitHub.connectUsingOAuth(ghToken);

                String login        = gitHub.getMyself().getLogin();
                String avatarUrl    = gitHub.getMyself().getAvatarUrl();
                String name         = gitHub.getMyself().getName();
                String htmlUrl      = gitHub.getMyself().getHtmlUrl().toString();

                githubUser = new GithubUser()
                        .setLogin(login)
                        .setAvatarUrl(avatarUrl)
                        .setName(name)
                        .setProfileLink(htmlUrl)
                        .setIdChatTelegram(chatId);

                githubUserService.add(githubUser);
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return githubUser;
    }

    @GetMapping("/repos")
    public List userRepositories(@RequestHeader(value = "token") String token) {
        List<Repository> myRepositories = new ArrayList<>();
        try {
            GitHub gitHub = GitHub.connectUsingOAuth(token);
            Collection<GHRepository> repositoriesFromGitHubApi = gitHub.getMyself().getAllRepositories().values();
            repositoriesFromGitHubApi.forEach(rep -> {
                List<GHCommit> ghCommits = rep.listCommits().asList();
                for (GHCommit ghCommit : ghCommits) {
                    try {
                        ghCommit.getCommitShortInfo().getCommitDate(); // нужное
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                String  fullName         = rep.getName();
                String  description      = rep.getDescription();
                String  htmlUrl          = rep.getHtmlUrl().toString();
                String  pushedAt         = rep.getPushedAt().toString();
                String  ownerLogin       = null;
                String  ownerHtmlUrl     = null;
                try {
                        ownerLogin       = rep.getOwner().getLogin();
                        ownerHtmlUrl     = rep.getOwner().getHtmlUrl().toString();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                myRepositories.add(new Repository()
                .setFullName(fullName)
                .setDescription(description)
                .setHtmlUrl(htmlUrl)
                .setPushedAt(pushedAt)
                .setOwnerLogin(ownerLogin)
                .setOwnerHtmlUrl(ownerHtmlUrl));

            });
            return myRepositories;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

*/
}
