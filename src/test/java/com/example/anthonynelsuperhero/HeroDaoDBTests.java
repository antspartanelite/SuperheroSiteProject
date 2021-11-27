package com.example.anthonynelsuperhero;

import com.example.anthonynelsuperhero.dao.HeroDao;
import com.example.anthonynelsuperhero.dao.OrganisationDao;
import com.example.anthonynelsuperhero.dao.SuperpowerDao;
import com.example.anthonynelsuperhero.dto.Hero;
import com.example.anthonynelsuperhero.dto.Organisation;
import com.example.anthonynelsuperhero.dto.Superpower;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class HeroDaoDBTests {

    @Autowired
    HeroDao heroDao;

    @Autowired
    OrganisationDao organisationDao;

    @Autowired
    SuperpowerDao superpowerDao;

    public HeroDaoDBTests(){
    }

    //Clears the relevant set of tables for these tests
    @BeforeEach
    public void setUp() {
        List<Hero> heroes = heroDao.getHeroes();
        for (Hero hero: heroes) {
            heroDao.deleteHeroById(hero.getId());
        }

        List<Organisation> organisations = organisationDao.getOrganisations();
        for (Organisation organisation : organisations) {
            organisationDao.deleteOrganisation(organisation.getId());
        }
        List<Superpower> superpowers = superpowerDao.getSuperpowers();
        for (Superpower superpower: superpowers) {
            superpowerDao.deleteSuperpower(superpower.getName());
        }
    }

    @Test
    public void addAndGetHeroesTest(){
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

        List<Hero> heroes = heroDao.getHeroes();

        assertEquals("Batman", heroes.get(0).getName());
        assertEquals("Wonder Woman", heroes.get(1).getName());
        assertEquals("Superman", heroes.get(2).getName());

        assertEquals("He runs around dressed in a bat costume", heroes.get(0).getDescription());
        assertEquals("She is an amazon", heroes.get(1).getDescription());
        assertEquals("Born on Krypton", heroes.get(2).getDescription());

        assertEquals("Money", heroes.get(0).getSuperpower());
        assertEquals("Strength", heroes.get(1).getSuperpower());
        assertEquals("Flight", heroes.get(2).getSuperpower());
    }

    @Test
    public void deleteHeroTest(){
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

        int wonderWomanID = heroDao.getHeroes().get(1).getId();
        heroDao.deleteHeroById(wonderWomanID);

        List<Hero> heroes = heroDao.getHeroes();
        assertEquals("Superman", heroes.get(1).getName());
        assertEquals("Born on Krypton", heroes.get(1).getDescription());
        assertEquals("Flight", heroes.get(1).getSuperpower());
    }

    @Test
    public void updateHeroTest() {
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

        heroToAdd = heroDao.getHeroes().get(2);
        heroToAdd.setSuperpower("Laser eyes");
        heroDao.updateHero(heroToAdd);


        List<Hero> heroes = heroDao.getHeroes();
        assertEquals("Superman", heroes.get(2).getName());
        assertEquals("Born on Krypton", heroes.get(2).getDescription());
        assertEquals("Laser eyes", heroes.get(2).getSuperpower());
    }

    @Test
    public void getOrganisationsOfHeroTest(){
        Organisation organisationToAdd = new Organisation();
        organisationToAdd.setName("The Justice League");
        organisationToAdd.setPostcode("00000");
        organisationToAdd.setDescription("Superheroes with a space station");
        organisationDao.addOrganisation(organisationToAdd);

        organisationToAdd.setName("The avengers");
        organisationToAdd.setPostcode("AM1224");
        organisationToAdd.setDescription("A marvel superhero group");
        organisationDao.addOrganisation(organisationToAdd);

        organisationToAdd.setName("The gotham heroes");
        organisationToAdd.setPostcode("GO6111");
        organisationToAdd.setDescription("A loose union of gotham superheroes");
        organisationDao.addOrganisation(organisationToAdd);

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

        List<Organisation> organisations = organisationDao.getOrganisations();
        List<Hero> heroes = heroDao.getHeroes();

        heroDao.addHeroToOrganisation(heroes.get(0).getId(),organisations.get(0).getId());
        heroDao.addHeroToOrganisation(heroes.get(1).getId(),organisations.get(0).getId());
        heroDao.addHeroToOrganisation(heroes.get(2).getId(),organisations.get(0).getId());
        heroDao.addHeroToOrganisation(heroes.get(0).getId(),organisations.get(2).getId());

        assertEquals("The Justice League",heroDao.getOrganisationsOfHero(heroes.get(0).getId()).get(0).getName());
        assertEquals("The gotham heroes",heroDao.getOrganisationsOfHero(heroes.get(0).getId()).get(1).getName());

        assertEquals(2, heroDao.getOrganisationsOfHero(heroes.get(0).getId()).size());
        assertEquals(1, heroDao.getOrganisationsOfHero(heroes.get(1).getId()).size());
        assertEquals(1, heroDao.getOrganisationsOfHero(heroes.get(2).getId()).size());
    }
}
