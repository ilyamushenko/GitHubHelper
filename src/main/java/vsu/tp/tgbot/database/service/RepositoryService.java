package vsu.tp.tgbot.database.service;


import vsu.tp.tgbot.database.models.Repository;

public interface RepositoryService {
    void add(Repository repo);
    Repository findByFullname(String fullName);
}
