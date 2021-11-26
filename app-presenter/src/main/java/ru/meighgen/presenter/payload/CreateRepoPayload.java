package ru.meighgen.presenter.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Create repo payload.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRepoPayload {
    /**
     * The Name.
     */
    protected String name;
    /**
     * The Owner.
     */
    protected Long owner;
    /**
     * The Folder id.
     */
    protected String folderId;
}
