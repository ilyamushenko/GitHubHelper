package vsu.tp.tgbot.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import vsu.tp.tgbot.database.models.GithubUser;
import vsu.tp.tgbot.database.models.Repository;
import vsu.tp.tgbot.database.service.implementations.GithubUserServiceImplementation;
import vsu.tp.tgbot.database.service.implementations.RepositoryServiceImplementation;
import vsu.tp.tgbot.exception.BaseException;

import javax.transaction.Transactional;
import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class RepoService {

    @Autowired
    private GithubUserServiceImplementation repositoryUser;

    @Autowired
    private RepositoryServiceImplementation repositoryRepo;

    public void subscribe(String login,
                          String fullName,
                          String htmlUrl,
                          String pushedAt,
                          String description,
                          String ownerLogin,
                          String ownerHtmlUrl
                          ) {
        GithubUser user = repositoryUser.findByLogin(login);
        if (user == null) {
            throw new BaseException("User with the given login does not exist",null);
        }
        else {
                Repository repository = new Repository(repositoryUser.findByLogin(login).getUserId(),
                        fullName, htmlUrl, ownerLogin, pushedAt, ownerHtmlUrl, description);
                //user.getRepositories().add(repository);
                repositoryRepo.add(repository);
        }
    }
/*
    @Transactional
    public void delete(Long userId, String fullNAme){
        repositoryRepo.deleteByUserIdAndFullName(userId, fullNAme);
    }*/
}
