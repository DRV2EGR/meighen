package ru.meighgen.presenter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Commit files dto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommitFilesDto {
    /**
     * The Name.
     */
    protected String name;
    /**
     * The File id.
     */
    protected String fileId;
}
