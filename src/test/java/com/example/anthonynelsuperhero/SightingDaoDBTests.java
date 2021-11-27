package com.example.anthonynelsuperhero;

import com.example.anthonynelsuperhero.dao.HeroDao;
import com.example.anthonynelsuperhero.dao.LocationDao;
import com.example.anthonynelsuperhero.dao.SightingDao;
import com.example.anthonynelsuperhero.dto.Hero;
import com.example.anthonynelsuperhero.dto.Location;
import com.example.anthonynelsuperhero.dto.Sighting;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SightingDaoDBTests {

    @Autowired
    SightingDao sightingDao;

    @Autowired
    LocationDao locationDao;

    @Autowired
    HeroDao heroDao;

    public SightingDaoDBTests(){}

    //Clears the relevant set of tables for these tests
    @BeforeEach
    public void setUp() {
        List<Sighting> sightings = sightingDao.getSightings();
        for (Sighting sighting : sightings) {
            sightingDao.deleteSighting(sighting);
        }
        List<Location> locations = locationDao.getLocations();
        for (Location location : locations) {
            locationDao.deleteLocation(location.getLatitude(), location.getLongitude());
        }
        List<Hero> heroes = heroDao.getHeroes();
        for (Hero hero: heroes) {
            heroDao.deleteHeroById(hero.getId());
        }
    }

    @Test
    public void addAndGetSightingsTest() {
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

        Hero heroToAdd = new Hero();
        heroToAdd.setName("Batman");
        heroToAdd.setDescription("He runs around dressed in a bat costume");
        heroToAdd.setSuperpower("Money");
        heroDao.addHero(heroToAdd);

        heroToAdd.setName("Wonder Woman");
        heroToAdd.setDescription("She is an amazon");
        heroToAdd.setSuperpower("Strength");
        heroDao.addHero(heroToAdd);

        heroToAdd.setName("Superman");
        heroToAdd.setDescription("Born on Krypton");
        heroToAdd.setSuperpower("Flight");
        heroDao.addHero(heroToAdd);


        Sighting sighting = new Sighting();
        sighting.setLatitude(locationDao.getLocations().get(0).getLatitude());
        sighting.setLongitude(locationDao.getLocations().get(0).getLongitude());
        sighting.setHeroId(heroDao.getHeroes().get(0).getId());
        sighting.setDate("2015-05-23");
        sightingDao.addSighting(sighting);

        sighting.setLatitude(locationDao.getLocations().get(0).getLatitude());
        sighting.setLongitude(locationDao.getLocations().get(0).getLongitude());
        sighting.setHeroId(heroDao.getHeroes().get(1).getId());
        sighting.setDate("2015-05-23");
        sightingDao.addSighting(sighting);

        sighting.setLatitude(locationDao.getLocations().get(1).getLatitude());
        sighting.setLongitude(locationDao.getLocations().get(1).getLongitude());
        sighting.setHeroId(heroDao.getHeroes().get(2).getId());
        sighting.setDate("2017-05-23");
        sightingDao.addSighting(sighting);

        List<Sighting> sightings = sightingDao.getSightings();

        assertEquals(heroDao.getHeroes().get(0).getId() , sightings.get(0).getHeroId());
        assertEquals(2.55f , sightings.get(0).getLatitude());
        assertEquals(2.88f , sightings.get(0).getLongitude());
        assertEquals("2015-05-23" , sightings.get(0).getDate());

        assertEquals(heroDao.getHeroes().get(1).getId() , sightings.get(1).getHeroId());
        assertEquals(2.55f , sightings.get(1).getLatitude());
        assertEquals(2.88f , sightings.get(1).getLongitude());
        assertEquals("2015-05-23" , sightings.get(1).getDate());

        assertEquals(heroDao.getHeroes().get(2).getId() , sightings.get(2).getHeroId());
        assertEquals(267.55f , sightings.get(2).getLatitude());
        assertEquals(222.88f , sightings.get(2).getLongitude());
        assertEquals("2017-05-23" , sightings.get(2).getDate());
    }

    @Test
    public void deleteSightingTest() {
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

        Hero heroToAdd = new Hero();
        heroToAdd.setName("Batman");
        heroToAdd.setDescription("He runs around dressed in a bat costume");
        heroToAdd.setSuperpower("Money");
        heroDao.addHero(heroToAdd);

        heroToAdd.setName("Wonder Woman");
        heroToAdd.setDescription("She is an amazon");
        heroToAdd.setSuperpower("Strength");
        heroDao.addHero(heroToAdd);

        heroToAdd.setName("Superman");
        heroToAdd.setDescription("Born on Krypton");
        heroToAdd.setSuperpower("Flight");
        heroDao.addHero(heroToAdd);

        Sighting sighting = new Sighting();
        sighting.setLatitude(locationDao.getLocations().get(0).getLatitude());
        sighting.setLongitude(locationDao.getLocations().get(0).getLongitude());
        sighting.setHeroId(heroDao.getHeroes().get(0).getId());
        sighting.setDate("2015-05-23");
        sightingDao.addSighting(sighting);

        sighting.setLatitude(locationDao.getLocations().get(0).getLatitude());
        sighting.setLongitude(locationDao.getLocations().get(0).getLongitude());
        sighting.setHeroId(heroDao.getHeroes().get(1).getId());
        sighting.setDate("2015-05-23");
        sightingDao.addSighting(sighting);

        sighting.setLatitude(locationDao.getLocations().get(1).getLatitude());
        sighting.setLongitude(locationDao.getLocations().get(1).getLongitude());
        sighting.setHeroId(heroDao.getHeroes().get(2).getId());
        sighting.setDate("2017-05-23");
        sightingDao.addSighting(sighting);

        List<Sighting> sightings = sightingDao.getSightings();
        sightingDao.deleteSighting(sightings.get(0));

        assertEquals(sightings.get(1).getLatitude(), sightingDao.getSightings().get(0).getLatitude());
        assertEquals(sightings.get(1).getLongitude(), sightingDao.getSightings().get(0).getLongitude());
        assertEquals(sightings.get(1).getHeroId(), sightingDao.getSightings().get(0).getHeroId());
        assertEquals(sightings.get(1).getDate(), sightingDao.getSightings().get(0).getDate());

        assertEquals(sightings.get(2).getLatitude(), sightingDao.getSightings().get(1).getLatitude());
        assertEquals(sightings.get(2).getLongitude(), sightingDao.getSightings().get(1).getLongitude());
        assertEquals(sightings.get(2).getHeroId(), sightingDao.getSightings().get(1).getHeroId());
        assertEquals(sightings.get(2).getDate(), sightingDao.getSightings().get(1).getDate());

        assertEquals(2, sightingDao.getSightings().size());
    }

    @Test
    public void updateSightingTest() {
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

        Hero heroToAdd = new Hero();
        heroToAdd.setName("Batman");
        heroToAdd.setDescription("He runs around dressed in a bat costume");
        heroToAdd.setSuperpower("Money");
        heroDao.addHero(heroToAdd);

        heroToAdd.setName("Wonder Woman");
        heroToAdd.setDescription("She is an amazon");
        heroToAdd.setSuperpower("Strength");
        heroDao.addHero(heroToAdd);

        heroToAdd.setName("Superman");
        heroToAdd.setDescription("Born on Krypton");
        heroToAdd.setSuperpower("Flight");
        heroDao.addHero(heroToAdd);


        Sighting sighting = new Sighting();
        sighting.setLatitude(locationDao.getLocations().get(0).getLatitude());
        sighting.setLongitude(locationDao.getLocations().get(0).getLongitude());
        sighting.setHeroId(heroDao.getHeroes().get(0).getId());
        sighting.setDate("2015-05-23");
        sightingDao.addSighting(sighting);

        sighting.setLatitude(locationDao.getLocations().get(0).getLatitude());
        sighting.setLongitude(locationDao.getLocations().get(0).getLongitude());
        sighting.setHeroId(heroDao.getHeroes().get(1).getId());
        sighting.setDate("2015-05-23");
        sightingDao.addSighting(sighting);

        sighting.setLatitude(locationDao.getLocations().get(1).getLatitude());
        sighting.setLongitude(locationDao.getLocations().get(1).getLongitude());
        sighting.setHeroId(heroDao.getHeroes().get(2).getId());
        sighting.setDate("2017-05-23");
        sightingDao.addSighting(sighting);

        List<Sighting> sightings = sightingDao.getSightings();

        sighting = sightings.get(1);
        sighting.setLatitude(267.55f);
        sighting.setLongitude(222.88f);
        sighting.setDate("2017-05-23");

        sightingDao.updateSighting(sighting);

        assertEquals(heroDao.getHeroes().get(0).getId() , sightings.get(0).getHeroId());
        assertEquals(2.55f , sightings.get(0).getLatitude());
        assertEquals(2.88f , sightings.get(0).getLongitude());
        assertEquals("2015-05-23" , sightings.get(0).getDate());

        assertEquals(heroDao.getHeroes().get(1).getId() , sightings.get(1).getHeroId());
        assertEquals(267.55f , sightingDao.getSightings().get(1).getLatitude());
        assertEquals(222.88f , sightingDao.getSightings().get(1).getLongitude());
        assertEquals("2017-05-23" , sightingDao.getSightings().get(1).getDate());

        assertEquals(heroDao.getHeroes().get(2).getId() , sightings.get(2).getHeroId());
        assertEquals(267.55f , sightings.get(2).getLatitude());
        assertEquals(222.88f , sightings.get(2).getLongitude());
        assertEquals("2017-05-23" , sightings.get(2).getDate());
    }

    @Test
    public void getHeroesByLocationSightedTest() {
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

        Hero heroToAdd = new Hero();
        heroToAdd.setName("Batman");
        heroToAdd.setDescription("He runs around dressed in a bat costume");
        heroToAdd.setSuperpower("Money");
        heroDao.addHero(heroToAdd);

        heroToAdd.setName("Wonder Woman");
        heroToAdd.setDescription("She is an amazon");
        heroToAdd.setSuperpower("Strength");
        heroDao.addHero(heroToAdd);

        heroToAdd.setName("Superman");
        heroToAdd.setDescription("Born on Krypton");
        heroToAdd.setSuperpower("Flight");
        heroDao.addHero(heroToAdd);


        Sighting sighting = new Sighting();
        sighting.setLatitude(locationDao.getLocations().get(0).getLatitude());
        sighting.setLongitude(locationDao.getLocations().get(0).getLongitude());
        sighting.setHeroId(heroDao.getHeroes().get(0).getId());
        sighting.setDate("2015-05-23");
        sightingDao.addSighting(sighting);

        sighting.setLatitude(locationDao.getLocations().get(0).getLatitude());
        sighting.setLongitude(locationDao.getLocations().get(0).getLongitude());
        sighting.setHeroId(heroDao.getHeroes().get(1).getId());
        sighting.setDate("2015-05-23");
        sightingDao.addSighting(sighting);

        sighting.setLatitude(locationDao.getLocations().get(1).getLatitude());
        sighting.setLongitude(locationDao.getLocations().get(1).getLongitude());
        sighting.setHeroId(heroDao.getHeroes().get(2).getId());
        sighting.setDate("2017-05-23");
        sightingDao.addSighting(sighting);

        assertEquals("Batman", sightingDao.getHeroesByLocationSighted(2.55f, 2.88f).get(0).getName());
        assertEquals("Wonder Woman", sightingDao.getHeroesByLocationSighted(2.55f, 2.88f).get(1).getName());
        assertEquals(2, sightingDao.getHeroesByLocationSighted(2.55f, 2.88f).size());

        assertEquals("Superman", sightingDao.getHeroesByLocationSighted(267.55f, 222.88f).get(0).getName());
        assertEquals(1, sightingDao.getHeroesByLocationSighted(267.55f, 222.88f).size());
    }

    @Test
    public void getLocationsHeroHasBeenSightedTest() {
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

        Hero heroToAdd = new Hero();
        heroToAdd.setName("Batman");
        heroToAdd.setDescription("He runs around dressed in a bat costume");
        heroToAdd.setSuperpower("Money");
        heroDao.addHero(heroToAdd);

        heroToAdd.setName("Wonder Woman");
        heroToAdd.setDescription("She is an amazon");
        heroToAdd.setSuperpower("Strength");
        heroDao.addHero(heroToAdd);

        heroToAdd.setName("Superman");
        heroToAdd.setDescription("Born on Krypton");
        heroToAdd.setSuperpower("Flight");
        heroDao.addHero(heroToAdd);


        Sighting sighting = new Sighting();
        sighting.setLatitude(locationDao.getLocations().get(0).getLatitude());
        sighting.setLongitude(locationDao.getLocations().get(0).getLongitude());
        sighting.setHeroId(heroDao.getHeroes().get(0).getId());
        sighting.setDate("2015-05-23");
        sightingDao.addSighting(sighting);

        sighting.setLatitude(locationDao.getLocations().get(0).getLatitude());
        sighting.setLongitude(locationDao.getLocations().get(0).getLongitude());
        sighting.setHeroId(heroDao.getHeroes().get(1).getId());
        sighting.setDate("2015-05-23");
        sightingDao.addSighting(sighting);

        sighting.setLatitude(locationDao.getLocations().get(1).getLatitude());
        sighting.setLongitude(locationDao.getLocations().get(1).getLongitude());
        sighting.setHeroId(heroDao.getHeroes().get(1).getId());
        sighting.setDate("2017-05-23");
        sightingDao.addSighting(sighting);

        sighting.setLatitude(locationDao.getLocations().get(1).getLatitude());
        sighting.setLongitude(locationDao.getLocations().get(1).getLongitude());
        sighting.setHeroId(heroDao.getHeroes().get(2).getId());
        sighting.setDate("2017-05-23");
        sightingDao.addSighting(sighting);

        assertEquals(2.55f, sightingDao.getLocationsHeroHasBeenSighted(heroDao.getHeroes().get(0).getId()).get(0).getLatitude());
        assertEquals(2.88f, sightingDao.getLocationsHeroHasBeenSighted(heroDao.getHeroes().get(0).getId()).get(0).getLongitude());
        assertEquals(1, sightingDao.getLocationsHeroHasBeenSighted(heroDao.getHeroes().get(0).getId()).size());

        assertEquals(2.55f, sightingDao.getLocationsHeroHasBeenSighted(heroDao.getHeroes().get(1).getId()).get(0).getLatitude());
        assertEquals(2.88f, sightingDao.getLocationsHeroHasBeenSighted(heroDao.getHeroes().get(1).getId()).get(0).getLongitude());
        assertEquals(267.55f, sightingDao.getLocationsHeroHasBeenSighted(heroDao.getHeroes().get(1).getId()).get(1).getLatitude());
        assertEquals(222.88f, sightingDao.getLocationsHeroHasBeenSighted(heroDao.getHeroes().get(1).getId()).get(1).getLongitude());
        assertEquals(2, sightingDao.getLocationsHeroHasBeenSighted(heroDao.getHeroes().get(1).getId()).size());

        assertEquals(267.55f, sightingDao.getLocationsHeroHasBeenSighted(heroDao.getHeroes().get(2).getId()).get(0).getLatitude());
        assertEquals(222.88f, sightingDao.getLocationsHeroHasBeenSighted(heroDao.getHeroes().get(2).getId()).get(0).getLongitude());
        assertEquals(1, sightingDao.getLocationsHeroHasBeenSighted(heroDao.getHeroes().get(2).getId()).size());
    }

    @Test
    public void getSightingsOnDateTest() {
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

        Hero heroToAdd = new Hero();
        heroToAdd.setName("Batman");
        heroToAdd.setDescription("He runs around dressed in a bat costume");
        heroToAdd.setSuperpower("Money");
        heroDao.addHero(heroToAdd);

        heroToAdd.setName("Wonder Woman");
        heroToAdd.setDescription("She is an amazon");
        heroToAdd.setSuperpower("Strength");
        heroDao.addHero(heroToAdd);

        heroToAdd.setName("Superman");
        heroToAdd.setDescription("Born on Krypton");
        heroToAdd.setSuperpower("Flight");
        heroDao.addHero(heroToAdd);


        Sighting sighting = new Sighting();
        sighting.setLatitude(locationDao.getLocations().get(0).getLatitude());
        sighting.setLongitude(locationDao.getLocations().get(0).getLongitude());
        sighting.setHeroId(heroDao.getHeroes().get(0).getId());
        sighting.setDate("2015-05-23");
        sightingDao.addSighting(sighting);

        sighting.setLatitude(locationDao.getLocations().get(0).getLatitude());
        sighting.setLongitude(locationDao.getLocations().get(0).getLongitude());
        sighting.setHeroId(heroDao.getHeroes().get(1).getId());
        sighting.setDate("2015-05-23");
        sightingDao.addSighting(sighting);

        sighting.setLatitude(locationDao.getLocations().get(1).getLatitude());
        sighting.setLongitude(locationDao.getLocations().get(1).getLongitude());
        sighting.setHeroId(heroDao.getHeroes().get(2).getId());
        sighting.setDate("2017-05-23");
        sightingDao.addSighting(sighting);

        assertEquals(heroDao.getHeroes().get(0).getId(), sightingDao.getSightingsOnDate("2015-05-23").get(0).getHeroId());
        assertEquals(heroDao.getHeroes().get(1).getId(), sightingDao.getSightingsOnDate("2015-05-23").get(1).getHeroId());
        assertEquals(2, sightingDao.getSightingsOnDate("2015-05-23").size());

        assertEquals(heroDao.getHeroes().get(2).getId(), sightingDao.getSightingsOnDate("2017-05-23").get(0).getHeroId());
        assertEquals(1, sightingDao.getSightingsOnDate("2017-05-23").size());
    }
}
