package ru.sbrf.ku.library.dao;

import ru.sbrf.ku.library.entities.Movement;

public interface MovementDao extends AbstractDao {
    void update(Movement movement);
}
