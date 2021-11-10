package ru.pominki.presenter.dto;

import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.pominki.presenter.entity.Commit;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommitDto {
    protected String commitId;
    protected String message;

    protected String previouse;
    protected String next;
}
