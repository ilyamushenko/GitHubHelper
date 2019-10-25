package vsu.tp.tgbot.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vsu.tp.tgbot.database.models.GithubUser;
import vsu.tp.tgbot.database.service.implementations.GithubUserServiceImplementation;
import vsu.tp.tgbot.exception.BaseException;

@Service
public class AuthService {

    @Autowired
    private GithubUserServiceImplementation repository;

    public void authenticate(String login, String token) {
        GithubUser user = repository.findByLogin(login);
        if (user == null) {
            throw new BaseException("User with the given login does not exist",null);
        } else {
            repository.findByLogin(login).setToken(token);
        }
    }

    public void register(String login, Long idChat, String token){
        GithubUser user = repository.findByLogin(login);
        if (user == null) {
            GithubUser newUser= new GithubUser(login, token, null, idChat, null,  null, null);
            repository.add(newUser);
        } else {
            throw new BaseException("User with the given login already exist", null);
        }
    }
}
