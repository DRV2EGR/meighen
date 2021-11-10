package ru.pominki.presenter.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.pominki.presenter.entity.Commit;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BranchDto {
    protected Long branchId;
    protected Long creatorId;
    protected String name;
    protected LocalDateTime timeOfBranchCreation;
    protected List<CommitDto> commits;
}
