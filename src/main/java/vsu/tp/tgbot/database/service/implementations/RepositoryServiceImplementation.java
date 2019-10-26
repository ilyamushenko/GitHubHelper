package vsu.tp.tgbot.database.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vsu.tp.tgbot.database.dao.RepositoryDAO;
import vsu.tp.tgbot.database.models.Repository;
import vsu.tp.tgbot.database.service.RepositoryService;

import java.util.List;

@Service
public class RepositoryServiceImplementation implements RepositoryService {

    private final RepositoryDAO repositoryDAO;

    @Autowired
    public RepositoryServiceImplementation(RepositoryDAO repositoryDAO) {
        this.repositoryDAO = repositoryDAO;
    }

    @Override
    public void add(Repository repo) {
        repositoryDAO.save(repo);
    }

    @Override
    public Repository findByFullname(String fullName) {
        return repositoryDAO.findByFullName(fullName);
    }

    @Override
    public List<Repository> findByUserID(Long userId) {
        return repositoryDAO.findByUserID(userId);
    }


}
