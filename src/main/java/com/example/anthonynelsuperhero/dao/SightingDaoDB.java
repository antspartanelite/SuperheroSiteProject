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

    @Override
    public void addSighting(Sighting sighting) {
        final String INSERT_SIGHTING= "INSERT INTO HeroSighting(Latitude, Longitude, HeroID, Time) "
                + "VALUES(?,?,?,?)";
        jdbc.update(INSERT_SIGHTING,
                sighting.getLatitude(),
                sighting.getLongitude(),
                sighting.getHeroId(),
                sighting.getTime());
    }

    @Override
    public void deleteSighting(Sighting sighting) {
        final String DELETE_HEROSIGHTING = "DELETE FROM HeroSighting WHERE Latitude = ? AND Longitude = ? AND HeroID = ? AND Time = ?";
        jdbc.update(DELETE_HEROSIGHTING,
                sighting.getLatitude(),
                sighting.getLongitude(),
                sighting.getHeroId(),
                sighting.getTime());
    }

    @Override
    public void updateSighting(Sighting sighting) {
        final String UPDATE_HEROSIGHTING= "UPDATE HeroSighting SET Latitude = ?, Longitude = ?, HeroID = ?, Time = ? "
                + "WHERE Latitude = ? AND Longitude = ? AND HeroID = ? AND Time = ?";
        jdbc.update(UPDATE_HEROSIGHTING,
                sighting.getLatitude(),
                sighting.getLongitude(),
                sighting.getHeroId(),
                sighting.getTime());
    }

    @Override
    public List<Sighting> getSightings() {
        final String SELECT_ALL_SIGHTINGS = "SELECT * FROM HeroSighting";
        List<Sighting> sightings = jdbc.query(SELECT_ALL_SIGHTINGS, new SightingMapper());
        return sightings;
    }

    @Override
    public List<Hero> getHeroesByLocationSighted(float latitude, float longitude) {
        final String SELECT_ALL_HEROES = "SELECT * FROM HeroSighting " +
                "INNER JOIN Hero ON Hero.HeroID = HeroSighting.HeroID WHERE Latitude = ? AND Longitude = ?";
        List<Hero> heroes = jdbc.query(SELECT_ALL_HEROES, new HeroHeroDaoDB.HeroMapper(), latitude, longitude);
        return heroes;
    }

    @Override
    public List<Location> getLocationsHeroHasBeenSighted(int heroId) {
        final String SELECT_ALL_LOCATIONS = "SELECT * FROM HeroSighting " +
                "INNER JOIN Location ON Hero.HeroID = HeroSighting.HeroID WHERE Latitude = ? AND Longitude = ?";
        List<Location> locations = jdbc.query(SELECT_ALL_LOCATIONS, new LocationDaoDB.LocationMapper(), heroId);
        return locations;
    }

    @Override
    public List<Sighting> getSightingsOnDate(String date) {
        final String SELECT_ALL_SIGHTINGS = "SELECT * FROM HeroSighting " +
                "WHERE Time = ?";
        List<Sighting> sightings = jdbc.query(SELECT_ALL_SIGHTINGS, new SightingMapper(), date);
        return sightings;
    }

    public static final class SightingMapper implements RowMapper<Sighting> {
        @Override
        public Sighting mapRow(ResultSet rs, int index) throws SQLException {
            Sighting sighting = new Sighting();
            sighting.setHeroId(rs.getInt("HeroID"));
            sighting.setLatitude(rs.getFloat("Latitude"));
            sighting.setLongitude(rs.getFloat("Longitude"));
            sighting.setTime(rs.getString("Time"));
            return sighting;
        }
    }
}
