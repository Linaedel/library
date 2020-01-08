package ru.sbrf.ku.library.dao.securityrepos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sbrf.ku.library.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}