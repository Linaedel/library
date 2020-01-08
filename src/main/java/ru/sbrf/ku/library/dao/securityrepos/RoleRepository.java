package ru.sbrf.ku.library.dao.securityrepos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sbrf.ku.library.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
