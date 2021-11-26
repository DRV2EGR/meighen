package ru.meighgen.presenter.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Branch dto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BranchDto {
    /**
     * The Branch id.
     */
    protected Long branchId;
    /**
     * The Creator id.
     */
    protected Long creatorId;
    /**
     * The Name.
     */
    protected String name;
    /**
     * The Time of branch creation.
     */
    protected LocalDateTime timeOfBranchCreation;
    /**
     * The Commits.
     */
    protected List<CommitDto> commits;
    /**
     * The Head.
     */
    protected String head;
}
