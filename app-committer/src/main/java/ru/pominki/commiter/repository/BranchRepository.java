package ru.pominki.commiter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pominki.commiter.entity.Branch;

public interface BranchRepository extends JpaRepository<Branch, Long> {
    Branch findBranchById(Long branchId);
}
