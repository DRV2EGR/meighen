package ru.meighgen.presenter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Create branch model.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBranchModel {
    /**
     * The Name.
     */
    protected String name;
    /**
     * The Owner.
     */
    protected Long owner;
    /**
     * The Repo id.
     */
    protected Long repoId;
}
