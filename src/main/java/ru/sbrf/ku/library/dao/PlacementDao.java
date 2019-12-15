package ru.sbrf.ku.library.dao;

import ru.sbrf.ku.library.entities.Placement;

import java.sql.SQLException;
import java.util.List;

public interface PlacementDao extends AbstractDao{
    List<Placement> list() throws SQLException;
}
