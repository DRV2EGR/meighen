package ru.pominki.presenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pominki.presenter.entity.Commit;

public interface CommitRepository extends JpaRepository<Commit, Long> {
    public Commit findCommitByCommitId(String commitId);
}
