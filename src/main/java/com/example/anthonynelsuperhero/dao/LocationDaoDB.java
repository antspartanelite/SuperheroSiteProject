package com.example.anthonynelsuperhero.dao;

import com.example.anthonynelsuperhero.dto.Hero;
import com.example.anthonynelsuperhero.dto.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LocationDaoDB implements LocationDao{

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public void addLocation(Location location) {
        final String INSERT_LOCATION = "INSERT INTO Hero(Name, Description, Postcode, Latitude, Longitude) "
                + "VALUES(?,?,?,?,?)";
        jdbc.update(INSERT_LOCATION,
                location.getName(),
                location.getDescription(),
                location.getPostcode(),
                location.getLatitude(),
                location.getLongitude());
    }

    @Override
    public void deleteLocation(float latitude, float longitude) {
        final String DELETE_HERO = "DELETE FROM Location WHERE Latitude = ? AND Longitude = ?";
        jdbc.update(DELETE_HERO, latitude, longitude);
    }

    @Override
    public void updateLocation(Location location) {
        final String UPDATE_LOCATION = "UPDATE Location SET Name = ?, Description = ? "
                + "WHERE Latitude = ? AND Longitude = ?";
        jdbc.update(UPDATE_LOCATION,
                location.getName(),
                location.getDescription(),
                location.getLongitude(),
                location.getLatitude());
    }

    @Override
    public List<Location> getLocations() {
        final String SELECT_ALL_LOCATIONS = "SELECT * FROM Location";
        List<Location> locations = jdbc.query(SELECT_ALL_LOCATIONS, new LocationMapper());
        return locations;
    }


    public static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int index) throws SQLException {
            Location location = new Location();
            location.setName(rs.getString("Name"));
            location.setDescription(rs.getString("Description"));
            location.setPostcode(rs.getString("Postcode"));
            location.setLatitude(rs.getFloat("Latitude"));
            location.setLongitude(rs.getFloat("Longitude"));
            return location;
        }
    }
}
