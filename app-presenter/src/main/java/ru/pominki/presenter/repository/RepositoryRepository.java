package ru.pominki.presenter.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pominki.presenter.entity.Repository;

public interface RepositoryRepository extends JpaRepository<Repository, Long> {
    public int countAllByName(String name);
    public Optional<Repository> findById(Long ownerId);
//    public Repository findRepositoryByOwner(Long ownerId);
}
