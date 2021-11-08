package ru.pominki.presenter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

/**
 * The type Role.
 */
@Entity
@Table(name = "roles")
@Data
public class Role extends BaseEntity {
    @Column
    private String name;
}
