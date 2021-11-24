package com.example.anthonynelsuperhero.dao;

import com.example.anthonynelsuperhero.dto.Location;

import java.util.List;

public interface LocationDao {
    public void addLocation(Location location);
    public void deleteLocation(float latitude, float longitude);
    public void updateLocation(Location location);
    public List<Location> getLocations();
}
