package ru.sbrf.ku.library.dao;

import ru.sbrf.ku.library.entities.Placement;

import java.util.List;

public interface PlacementDao extends AbstractDao{
    List<Placement> list();
    Placement get(Long id);
}
