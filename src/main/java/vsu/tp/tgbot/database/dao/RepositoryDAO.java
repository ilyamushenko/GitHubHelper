package vsu.tp.tgbot.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import vsu.tp.tgbot.database.models.Repository;

import java.util.List;

public interface RepositoryDAO extends JpaRepository<Repository, Integer> {
    Repository findByFullName(String fullName);

    @Query("select u from Repository u where u.userId = ?1")
    List<Repository> findByUserID(Long userId);

    @Modifying
    @Transactional
    @Query("delete from Repository u where u.userId = ?1 and u.fullName = ?2")
    void deleteByUserID(Long userId, String fullName);

    @Modifying
    @Transactional
    void deleteByUserIdAndFullName(Long userId, String fullName);
}
