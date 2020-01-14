package ru.sbrf.ku.library.services;

import ru.sbrf.ku.library.entities.Movement;

public interface MovementService {
    void add(Movement movement);
    void update(Movement movement);
}
