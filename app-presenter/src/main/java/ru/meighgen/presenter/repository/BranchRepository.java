package ru.meighgen.presenter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.meighgen.presenter.entity.Branch;

/**
 * The interface Branch repository.
 */
public interface BranchRepository extends JpaRepository<Branch, String> {
    /**
     * Find all by creator list.
     *
     * @param creatorId the creator id
     * @return the list
     */
    List<Branch> findAllByCreator(Long creatorId);

    /**
     * Find branch by id branch.
     *
     * @param branchId the branch id
     * @return the branch
     */
    Branch findBranchById(Long branchId);
}
