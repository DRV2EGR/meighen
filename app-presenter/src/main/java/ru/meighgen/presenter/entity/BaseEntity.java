package ru.meighgen.presenter.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Data;

/**
 * The type Base entity.
 */
@MappedSuperclass
@Data
public abstract class BaseEntity {
    /**
     * The Id.
     */
    @Id
    @GeneratedValue(generator = "increment")
    protected long id;
}
