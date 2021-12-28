package ru.meighgen.presenter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Commit dto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommitDto {
    /**
     * The Commit id.
     */
    protected String commitId;
    /**
     * The Message.
     */
    protected String message;

    /**
     * The Next.
     */
    protected String next;
}
