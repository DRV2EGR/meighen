package ru.meighgen.presenter.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Create branch payload.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBranchPayload {
    /**
     * The Repo id.
     */
    protected Long repoId;
    /**
     * The Name.
     */
    protected String name;
}
