package ru.meighgen.commiter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.meighgen.commiter.entity.Repository;

/**
 * The interface Repository repository.
 */
public interface RepositoryRepository extends JpaRepository<Repository, Long> {
}
