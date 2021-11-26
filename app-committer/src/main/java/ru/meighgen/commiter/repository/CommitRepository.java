package ru.meighgen.commiter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.meighgen.commiter.entity.Commit;

/**
 * The interface Commit repository.
 */
public interface CommitRepository extends JpaRepository<Commit, Long> {
    /**
     * Find commit by commit id commit.
     *
     * @param commitId the commit id
     * @return the commit
     */
    Commit findCommitByCommitId(String commitId);
}
