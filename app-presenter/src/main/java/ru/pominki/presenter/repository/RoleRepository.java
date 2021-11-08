package ru.pominki.presenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pominki.presenter.entity.Role;

/**
 * The interface Role repository.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
}
