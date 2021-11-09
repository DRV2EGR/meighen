package ru.pominki.presenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pominki.presenter.entity.Repository;

public interface RepositoryRepository extends JpaRepository<Repository, Long> {
    public int countAllByName(String name);
}
