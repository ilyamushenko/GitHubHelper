package vsu.tp.tgbot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vsu.tp.tgbot.database.service.implementations.GithubUserServiceImplementation;
import vsu.tp.tgbot.database.service.implementations.RepositoryServiceImplementation;
import vsu.tp.tgbot.response.ApiResponse;
import vsu.tp.tgbot.response.Login;
import vsu.tp.tgbot.response.SubscribeRepo;
import vsu.tp.tgbot.service.RepoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/repo")
public class RepositoryControllerTP {

    @Autowired
    private GithubUserServiceImplementation repositoryUser;

    @Autowired
    private RepositoryServiceImplementation repositoryRepo;

    @Autowired
    private RepoService repoService;

    @RequestMapping(value ="subscribe", method = RequestMethod.POST)
    public ApiResponse<Void> subscribePoint(@RequestBody SubscribeRepo subscribeRepo) {
        repoService.subscribe(subscribeRepo.getLogin(),
                subscribeRepo.getFullName(),
                subscribeRepo.getHtmlUrl(),
                subscribeRepo.getPushedAt(),
                subscribeRepo.getDescription(),
                subscribeRepo.getOwnerLogin(),
                subscribeRepo.getOwnerHtmlUrl());
        return new ApiResponse<>("Success");
    }

    @RequestMapping(value ="list-repo", method = RequestMethod.GET)
    public ApiResponse<Void> listRepo(@RequestBody Login login ) {
        return new ApiResponse<>(repositoryUser.findByLogin(login.getLogin()).getRepositories());
    }
}
