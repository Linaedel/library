package ru.sbrf.ku.library.services;

import ru.sbrf.ku.library.entities.Placement;

import java.util.Collection;

public interface PlacementService {
    Collection<Placement> list();
    Placement get(Long id);
}
