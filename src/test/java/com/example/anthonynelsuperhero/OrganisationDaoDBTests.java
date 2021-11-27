package com.example.anthonynelsuperhero;

import com.example.anthonynelsuperhero.dao.HeroDao;
import com.example.anthonynelsuperhero.dao.OrganisationDao;
import com.example.anthonynelsuperhero.dto.Hero;
import com.example.anthonynelsuperhero.dto.Organisation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class OrganisationDaoDBTests {

    @Autowired
    OrganisationDao organisationDao;

    @Autowired
    HeroDao heroDao;

    public OrganisationDaoDBTests(){
    }

    //Clears the relevant set of tables for these tests
    @BeforeEach
    public void setUp() {
        List<Organisation> organisations = organisationDao.getOrganisations();
        for (Organisation organisation : organisations) {
            organisationDao.deleteOrganisation(organisation.getId());
        }
        List<Hero> heroes = heroDao.getHeroes();
        for (Hero hero: heroes) {
            heroDao.deleteHeroById(hero.getId());
        }
    }

    @Test
    public void addAndGetOrganisationsTest(){
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

        List<Organisation> organisations = organisationDao.getOrganisations();

        assertEquals("The Justice League", organisations.get(0).getName());
        assertEquals("00000", organisations.get(0).getPostcode());
        assertEquals("Superheroes with a space station", organisations.get(0).getDescription());

        assertEquals("The avengers", organisations.get(1).getName());
        assertEquals("AM1224", organisations.get(1).getPostcode());
        assertEquals("A marvel superhero group", organisations.get(1).getDescription());

        assertEquals("The gotham heroes", organisations.get(2).getName());
        assertEquals("GO6111", organisations.get(2).getPostcode());
        assertEquals("A loose union of gotham superheroes", organisations.get(2).getDescription());
    }

    @Test
    public void deleteOrganisationTest(){
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

        List<Organisation> organisations = organisationDao.getOrganisations();

        organisationDao.deleteOrganisation(organisations.get(1).getId());

        organisations = organisationDao.getOrganisations();

        assertEquals("The Justice League", organisations.get(0).getName());
        assertEquals("00000", organisations.get(0).getPostcode());
        assertEquals("Superheroes with a space station", organisations.get(0).getDescription());

        assertEquals("The gotham heroes", organisations.get(1).getName());
        assertEquals("GO6111", organisations.get(1).getPostcode());
        assertEquals("A loose union of gotham superheroes", organisations.get(1).getDescription());
    }

    @Test
    public void updateOrganisationTest(){
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

        List<Organisation> organisations = organisationDao.getOrganisations();

        organisationToAdd = organisations.get(1);
        organisationToAdd.setPostcode("AVGHQ1");

        organisationDao.updateOrganisation(organisationToAdd);

        organisations = organisationDao.getOrganisations();

        assertEquals("The Justice League", organisations.get(0).getName());
        assertEquals("00000", organisations.get(0).getPostcode());
        assertEquals("Superheroes with a space station", organisations.get(0).getDescription());

        assertEquals("The avengers", organisations.get(1).getName());
        assertEquals("AVGHQ1", organisations.get(1).getPostcode());
        assertEquals("A marvel superhero group", organisations.get(1).getDescription());

        assertEquals("The gotham heroes", organisations.get(2).getName());
        assertEquals("GO6111", organisations.get(2).getPostcode());
        assertEquals("A loose union of gotham superheroes", organisations.get(2).getDescription());
    }

    @Test
    public void getHeroesOfOrganisationTest(){
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

        assertEquals("Batman", organisationDao.getHeroesOfOrganisation(organisations.get(0).getId()).get(0).getName());
        assertEquals("Wonder Woman", organisationDao.getHeroesOfOrganisation(organisations.get(0).getId()).get(1).getName());
        assertEquals("Superman", organisationDao.getHeroesOfOrganisation(organisations.get(0).getId()).get(2).getName());

        assertEquals(0, organisationDao.getHeroesOfOrganisation(organisations.get(1).getId()).size());

        assertEquals("Batman", organisationDao.getHeroesOfOrganisation(organisations.get(2).getId()).get(0).getName());

    }

}
