package com.example.anthonynelsuperhero.dao;

import com.example.anthonynelsuperhero.dto.Hero;
import com.example.anthonynelsuperhero.dto.Location;
import com.example.anthonynelsuperhero.dto.Sighting;

import java.util.List;

public interface SightingDao {
    public void addSighting (Sighting sighting);
    public void deleteSighting(int id);
    public void updateSighting(Sighting sighting);
    public List<Sighting> getSightings();

    public List<Hero> getHeroesByLocationSighted(float latitude, float longitude);
    public List<Location> getLocationsHeroHasBeenSighted(int heroId);
    public List<Sighting> getSightingsOnDate(String date);
    public List<Sighting> getRecentSightings();
}
