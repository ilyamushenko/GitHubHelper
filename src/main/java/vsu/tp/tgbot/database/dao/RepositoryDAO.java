package vsu.tp.tgbot.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import vsu.tp.tgbot.database.models.Repository;

public interface RepositoryDAO extends JpaRepository<Repository, Integer> {
}
