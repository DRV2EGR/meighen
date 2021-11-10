package ru.pominki.presenter.repository;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.pominki.presenter.entity.Branch;
import ru.pominki.presenter.entity.RefreshToken;

public interface BranchRepository extends JpaRepository<Branch, String> {
    List<Branch> findAllByCreator(Long creatorId);
}
