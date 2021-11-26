package ru.meighgen.presenter.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Repo dto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepoDto {
    /**
     * The Id.
     */
    protected Long ID;
    /**
     * The Name.
     */
    protected String name;
    /**
     * The Time of repo creation.
     */
    protected LocalDateTime timeOfRepoCreation;

    /**
     * The Main branch.
     */
    protected String mainBranch;

    /**
     * The Branches.
     */
    protected List<BranchDto> branches;
    /**
     * The Collaborators.
     */
    protected List<UserDto> collaborators;
}
