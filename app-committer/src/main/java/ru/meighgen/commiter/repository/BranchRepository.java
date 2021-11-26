package ru.meighgen.commiter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.meighgen.commiter.entity.Branch;

/**
 * The interface Branch repository.
 */
public interface BranchRepository extends JpaRepository<Branch, Long> {
    /**
     * Find branch by id branch.
     *
     * @param branchId the branch id
     * @return the branch
     */
    Branch findBranchById(Long branchId);
}
