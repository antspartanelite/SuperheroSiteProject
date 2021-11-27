package com.example.anthonynelsuperhero.dao;

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

    //Adds a new location to the location table in the DB
    @Override
    public void addLocation(Location location) {
        final String INSERT_LOCATION = "INSERT INTO Location(Name, Description, Postcode, Latitude, Longitude) "
                + "VALUES(?,?,?,?,?)";
        jdbc.update(INSERT_LOCATION,
                location.getName(),
                location.getDescription(),
                location.getPostcode(),
                location.getLatitude(),
                location.getLongitude());
    }

    //Deletes a location from the DB
    @Override
    public void deleteLocation(float latitude, float longitude) {
        final String DELETE_HERO = "DELETE FROM Location WHERE Latitude = "+(double)latitude+" AND Longitude = "+(double)longitude;
        //Cast to double as it had trouble removing floats for some reason, will refactor if time allows
        jdbc.update(DELETE_HERO);
    }

    //Updates a location in the DB
    @Override
    public void updateLocation(Location location) {
        final String UPDATE_LOCATION = "UPDATE Location SET Name = ?, Description = ? "
                + "WHERE Latitude = "+(double)location.getLatitude()+" AND Longitude = "+(double)location.getLongitude();
        jdbc.update(UPDATE_LOCATION,
                location.getName(),
                location.getDescription());
    }

    //Gets all the location in the location table in the DB
    @Override
    public List<Location> getLocations() {
        final String SELECT_ALL_LOCATIONS = "SELECT * FROM Location";
        List<Location> locations = jdbc.query(SELECT_ALL_LOCATIONS, new LocationMapper());
        return locations;
    }

    //Maps a row from the location table to a location object
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
