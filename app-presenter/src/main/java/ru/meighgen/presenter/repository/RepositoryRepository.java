package ru.meighgen.presenter.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.meighgen.presenter.entity.Repository;
import ru.meighgen.presenter.entity.User;

/**
 * The interface Repository repository.
 */
public interface RepositoryRepository extends JpaRepository<Repository, Long> {
    /**
     * Count all by name int.
     *
     * @param name the name
     * @return the int
     */
    public int countAllByName(String name);
    public Optional<Repository> findById(Long ownerId);
//    public Repository findRepositoryByOwner(Long ownerId);
    public List<Repository> findAllByCollaboratorsContains(User collaborators);
}
