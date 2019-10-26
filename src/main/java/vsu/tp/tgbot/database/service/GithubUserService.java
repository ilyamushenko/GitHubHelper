package vsu.tp.tgbot.database.service;

import vsu.tp.tgbot.database.models.GithubUser;

public interface GithubUserService {
    GithubUser getByUserId(Long userId);
    GithubUser getByIdChatTelegram(Long idChatTelegram);
    GithubUser findByIdChatTelegram(Long idChat);
    GithubUser findByLogin(String login);
    void add(GithubUser user);
}
