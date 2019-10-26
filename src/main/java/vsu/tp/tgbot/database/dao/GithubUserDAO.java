package vsu.tp.tgbot.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import vsu.tp.tgbot.database.models.GithubUser;

public interface GithubUserDAO extends JpaRepository<GithubUser, Integer> {
    GithubUser getByUserId(Long userId);
    GithubUser getByIdChatTelegram(Long chatId);
    GithubUser findByIdChatTelegram(Long chatId);

}
