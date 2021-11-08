package ru.pominki.commiter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pominki.commiter.entity.Role;

/**
 * The interface Role repository.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
}
