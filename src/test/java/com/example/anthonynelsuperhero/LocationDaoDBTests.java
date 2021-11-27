package com.example.anthonynelsuperhero;

import com.example.anthonynelsuperhero.dao.LocationDao;
import com.example.anthonynelsuperhero.dto.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LocationDaoDBTests {

    @Autowired
    LocationDao locationDao;

    public LocationDaoDBTests(){
    }

    //Clears the relevant set of tables for these tests
    @BeforeEach
    public void setUp() {
        List<Location> locations = locationDao.getLocations();
        for (Location location : locations) {
            locationDao.deleteLocation(location.getLatitude(), location.getLongitude());
        }
    }

    @Test
    public void addAndGetLocationsTest(){
        Location locationToAdd = new Location();
        locationToAdd.setLatitude(2.55f);
        locationToAdd.setLongitude(2.88f);
        locationToAdd.setPostcode("GH5665");
        locationToAdd.setName("Gotham cheese factory");
        locationToAdd.setDescription("A place where cheese is made in the city of crime");
        locationDao.addLocation(locationToAdd);

        locationToAdd.setLatitude(267.55f);
        locationToAdd.setLongitude(222.88f);
        locationToAdd.setPostcode("M44665");
        locationToAdd.setName("Metropolis Square");
        locationToAdd.setDescription("Central area in metropolis");
        locationDao.addLocation(locationToAdd);


        List<Location> locations = locationDao.getLocations();

        assertEquals(2.55f , locations.get(0).getLatitude());
        assertEquals(2.88f, locations.get(0).getLongitude());
        assertEquals("GH5665", locations.get(0).getPostcode());
        assertEquals("Gotham cheese factory", locations.get(0).getName());
        assertEquals("A place where cheese is made in the city of crime", locations.get(0).getDescription());

        assertEquals(267.55f , locations.get(1).getLatitude());
        assertEquals(222.88f, locations.get(1).getLongitude());
        assertEquals("M44665", locations.get(1).getPostcode());
        assertEquals("Metropolis Square", locations.get(1).getName());
        assertEquals("Central area in metropolis", locations.get(1).getDescription());
    }

    @Test
    public void deleteLocationTest(){
        Location locationToAdd = new Location();
        locationToAdd.setLatitude(2.55f);
        locationToAdd.setLongitude(2.88f);
        locationToAdd.setPostcode("GH5665");
        locationToAdd.setName("Gotham cheese factory");
        locationToAdd.setDescription("A place where cheese is made in the city of crime");
        locationDao.addLocation(locationToAdd);

        locationToAdd.setLatitude(267.55f);
        locationToAdd.setLongitude(222.88f);
        locationToAdd.setPostcode("M44665");
        locationToAdd.setName("Metropolis Square");
        locationToAdd.setDescription("Central area in metropolis");
        locationDao.addLocation(locationToAdd);

        locationDao.deleteLocation(2.55f, 2.88f);

        List<Location> locations = locationDao.getLocations();

        assertEquals(267.55f , locations.get(0).getLatitude());
        assertEquals(222.88f, locations.get(0).getLongitude());
        assertEquals("M44665", locations.get(0).getPostcode());
        assertEquals("Metropolis Square", locations.get(0).getName());
        assertEquals("Central area in metropolis", locations.get(0).getDescription());
    }

    @Test
    public void updateLocationTest(){
        Location locationToAdd = new Location();
        locationToAdd.setLatitude(2.55f);
        locationToAdd.setLongitude(2.88f);
        locationToAdd.setPostcode("GH5665");
        locationToAdd.setName("Gotham cheese factory");
        locationToAdd.setDescription("A place where cheese is made in the city of crime");
        locationDao.addLocation(locationToAdd);

        locationToAdd.setLatitude(267.55f);
        locationToAdd.setLongitude(222.88f);
        locationToAdd.setPostcode("M44665");
        locationToAdd.setName("Metropolis Square");
        locationToAdd.setDescription("Central area in metropolis");
        locationDao.addLocation(locationToAdd);

        locationToAdd.setDescription("Newly destroyed area in metropolis");

        locationDao.updateLocation(locationToAdd);

        List<Location> locations = locationDao.getLocations();

        assertEquals(267.55f , locations.get(1).getLatitude());
        assertEquals(222.88f, locations.get(1).getLongitude());
        assertEquals("M44665", locations.get(1).getPostcode());
        assertEquals("Metropolis Square", locations.get(1).getName());
        assertEquals("Newly destroyed area in metropolis", locations.get(1).getDescription());
    }
}
