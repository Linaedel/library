package ru.sbrf.ku.library.controller.builders;

import ru.sbrf.ku.library.dao.PlacementDao;
import ru.sbrf.ku.library.entities.Placement;

public class PlacementBuilder {
    private Placement placement;
    private PlacementDao placementDao;

    public PlacementBuilder(PlacementDao placementDao) {
        this.placement = new Placement();
        this.placementDao = placementDao;
    }

    public Placement build() {
        placement.setType(1);
        placementDao.add(placement);
        return placement;
    }

    public PlacementBuilder setName(String name){
        placement.setName(name);
        return this;
    }

    public PlacementBuilder setDescription( String description ) {
        placement.setDescription(description);
        return this;
    }
}
