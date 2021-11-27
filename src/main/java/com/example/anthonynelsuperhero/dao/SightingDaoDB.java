package com.example.anthonynelsuperhero.dao;

import com.example.anthonynelsuperhero.dto.Hero;
import com.example.anthonynelsuperhero.dto.Location;
import com.example.anthonynelsuperhero.dto.Sighting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SightingDaoDB implements SightingDao{

    @Autowired
    JdbcTemplate jdbc;

    //Adds a new sighting to the DB
    @Override
    public void addSighting(Sighting sighting) {
        final String INSERT_SIGHTING= "INSERT INTO HeroSighting(Latitude, Longitude, HeroID, SightingDate) "
                + "VALUES(?,?,?,?)";
        jdbc.update(INSERT_SIGHTING,
                sighting.getLatitude(),
                sighting.getLongitude(),
                sighting.getHeroId(),
                sighting.getDate());
    }

    //Deletes a sighting from the DB
    @Override
    public void deleteSighting(Sighting sighting) {
        final String DELETE_HEROSIGHTING = "DELETE FROM HeroSighting WHERE SightingID = ?";
        jdbc.update(DELETE_HEROSIGHTING,
                sighting.getSightingId());
    }

    //Updates a sighting currently in the db
    @Override
    public void updateSighting(Sighting sighting) {
        final String UPDATE_HEROSIGHTING= "UPDATE HeroSighting SET Latitude = ?, Longitude = ?, HeroID = ?, SightingDate = ? "
                + "WHERE SightingID = ?";
        jdbc.update(UPDATE_HEROSIGHTING,
                sighting.getLatitude(),
                sighting.getLongitude(),
                sighting.getHeroId(),
                sighting.getDate(),
                sighting.getSightingId());
    }

    //gets all the sighting currently in the db and returns as a list
    @Override
    public List<Sighting> getSightings() {
        final String SELECT_ALL_SIGHTINGS = "SELECT * FROM HeroSighting";
        List<Sighting> sightings = jdbc.query(SELECT_ALL_SIGHTINGS, new SightingMapper());
        return sightings;
    }

    //gets all the heroes in the db sighted at a specified location
    @Override
    public List<Hero> getHeroesByLocationSighted(float latitude, float longitude) {
        final String SELECT_ALL_HEROES = "SELECT * FROM HeroSighting " +
                "INNER JOIN Hero ON Hero.HeroID = HeroSighting.HeroID WHERE Latitude = ? AND Longitude = ?";
        List<Hero> heroes = jdbc.query(SELECT_ALL_HEROES, new HeroDaoDB.HeroMapper(), (double)latitude, (double)longitude);
        return heroes;
    }

    //gets all the locations that a specified hero has been sighted
    @Override
    public List<Location> getLocationsHeroHasBeenSighted(int heroId) {
        final String SELECT_ALL_LOCATIONS = "SELECT * FROM HeroSighting " +
                "INNER JOIN Location ON Location.Latitude = HeroSighting.Latitude AND Location.Longitude = HeroSighting.Longitude WHERE HeroID = ?";
        List<Location> locations = jdbc.query(SELECT_ALL_LOCATIONS, new LocationDaoDB.LocationMapper(), heroId);
        return locations;
    }

    //gets all of the sightings on a specified date
    @Override
    public List<Sighting> getSightingsOnDate(String date) {
        final String SELECT_ALL_SIGHTINGS = "SELECT * FROM HeroSighting " +
                "WHERE SightingDate = ?";
        List<Sighting> sightings = jdbc.query(SELECT_ALL_SIGHTINGS, new SightingMapper(), date);
        return sightings;
    }

    //maps an sighting entry from the db into a sighting object
    public static final class SightingMapper implements RowMapper<Sighting> {
        @Override
        public Sighting mapRow(ResultSet rs, int index) throws SQLException {
            Sighting sighting = new Sighting();
            sighting.setSightingId(rs.getInt("SightingID"));
            sighting.setHeroId(rs.getInt("HeroID"));
            sighting.setLatitude(rs.getFloat("Latitude"));
            sighting.setLongitude(rs.getFloat("Longitude"));
            sighting.setDate(rs.getString("SightingDate"));
            return sighting;
        }
    }
}
