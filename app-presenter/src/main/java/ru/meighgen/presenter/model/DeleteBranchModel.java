package ru.meighgen.presenter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Delete branch model.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteBranchModel {
    /**
     * The Branch id.
     */
    protected Long branchId;
}
