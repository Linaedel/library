package ru.sbrf.ku.library.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sbrf.ku.library.dao.PlacementDao;
import ru.sbrf.ku.library.entities.Placement;
import ru.sbrf.ku.library.services.PlacementService;

import java.util.Collection;
@Service
public class PlacementServiceImpl implements PlacementService {
    private PlacementDao placementDao;

    @Autowired
    public PlacementServiceImpl(PlacementDao placementDao) {
        this.placementDao = placementDao;
    }

    @Override
    public Collection<Placement> list() {
        return placementDao.list();
    }

    @Override
    public Placement get(Long id) {
        return placementDao.get(id);
    }
}
