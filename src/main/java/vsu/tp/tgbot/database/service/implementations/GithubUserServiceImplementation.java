package vsu.tp.tgbot.database.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vsu.tp.tgbot.database.dao.GithubUserDAO;
import vsu.tp.tgbot.database.models.GithubUser;
import vsu.tp.tgbot.database.service.GithubUserService;

@Service
public class GithubUserServiceImplementation implements GithubUserService {

    private final GithubUserDAO githubUserDAO;

    @Autowired
    public GithubUserServiceImplementation(GithubUserDAO githubUserDAO) {
        this.githubUserDAO = githubUserDAO;
    }

    @Override
    public GithubUser getByUserId(Long userId) {
        return githubUserDAO.getByUserId(userId);
    }

    @Override
    public GithubUser getByIdChatTelegram(Long idChatTelegram) {
        return githubUserDAO.getByIdChatTelegram(idChatTelegram);
    }
}
