package ru.meighgen.presenter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Repo short dto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepoShortDto {
    /**
     * The Id.
     */
    protected Long ID;
    /**
     * The Name.
     */
    protected String name;
}
