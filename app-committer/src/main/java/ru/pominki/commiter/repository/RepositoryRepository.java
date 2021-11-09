package ru.pominki.commiter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pominki.commiter.entity.Repository;

public interface RepositoryRepository extends JpaRepository<Repository, Long> {
}
