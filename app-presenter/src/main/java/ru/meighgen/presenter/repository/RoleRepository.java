package ru.meighgen.presenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.meighgen.presenter.entity.Role;

/**
 * The interface Role repository.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
}
