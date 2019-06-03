package vsu.tp.tgbot.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vsu.tp.tgbot.database.service.GithubUserService;
import vsu.tp.tgbot.database.service.RepositoryService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/search")
public class SearchController {

    private final GithubUserService githubUserService;
    private final RepositoryService repositoryService;

    @Autowired
    public SearchController(GithubUserService githubUserService, RepositoryService repositoryService) {
        this.githubUserService = githubUserService;
        this.repositoryService = repositoryService;
    }
}
