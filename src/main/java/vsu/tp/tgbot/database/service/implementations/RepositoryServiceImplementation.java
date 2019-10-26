package vsu.tp.tgbot.database.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vsu.tp.tgbot.database.dao.RepositoryDAO;
import vsu.tp.tgbot.database.service.RepositoryService;

@Service
public class RepositoryServiceImplementation implements RepositoryService {

    private final RepositoryDAO repositoryDAO;

    @Autowired
    public RepositoryServiceImplementation(RepositoryDAO repositoryDAO) {
        this.repositoryDAO = repositoryDAO;
    }
}
