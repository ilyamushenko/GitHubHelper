package vsu.tp.tgbot.database.service;


import vsu.tp.tgbot.database.models.Repository;

import java.util.List;

public interface RepositoryService {
    void add(Repository repo);
    Repository findByFullname(String fullName);
    List<Repository> findByUserID(Long userId);
}
