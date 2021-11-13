package ru.pominki.commiter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pominki.commiter.entity.Commit;

public interface CommitRepository extends JpaRepository<Commit, Long> {
    Commit findCommitByCommitId(String commitId);
}
