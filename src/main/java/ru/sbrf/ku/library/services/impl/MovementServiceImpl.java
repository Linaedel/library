package ru.sbrf.ku.library.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sbrf.ku.library.dao.MovementDao;
import ru.sbrf.ku.library.entities.Movement;
import ru.sbrf.ku.library.services.MovementService;

@Service
public class MovementServiceImpl implements MovementService {
    MovementDao movementDao;

    @Autowired
    public MovementServiceImpl(MovementDao movementDao) {
        this.movementDao = movementDao;
    }

    @Override
    public void add(Movement movement) {
        movementDao.add(movement);
    }

    @Override
    public void update(Movement movement) {
        movementDao.update(movement);
    }
}
