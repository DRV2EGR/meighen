package ru.meighgen.presenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.meighgen.presenter.entity.Commit;

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
    public Commit findCommitByCommitId(String commitId);
}
