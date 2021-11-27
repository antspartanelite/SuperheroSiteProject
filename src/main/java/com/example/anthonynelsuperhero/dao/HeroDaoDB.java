package com.example.anthonynelsuperhero.dao;

import com.example.anthonynelsuperhero.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Repository
public class HeroDaoDB implements HeroDao {

    @Autowired
    JdbcTemplate jdbc;

    @Autowired
    SuperpowerDao superpowerDao;

    @Override
    public void addHero(Hero hero) {
        final String INSERT_HERO = "INSERT INTO Hero(Name, Description, Superpower) "
                + "VALUES(?,?,?)";
        handleNewSuperpower(hero.getSuperpower());
        jdbc.update(INSERT_HERO,
                hero.getName(),
                hero.getDescription(),
                hero.getSuperpower());
    }

    @Override
    public void deleteHeroById(int id) {
        final String DELETE_HERO = "DELETE FROM Hero WHERE HeroID = ?";
        jdbc.update(DELETE_HERO, id);
    }

    @Override
    public void updateHero(Hero hero) {
        final String UPDATE_HERO = "UPDATE Hero SET Name = ?, Description = ?, "
                + "Superpower = ? WHERE HeroID = ?";
        handleNewSuperpower(hero.getSuperpower());
        jdbc.update(UPDATE_HERO,
                hero.getName(),
                hero.getDescription(),
                hero.getSuperpower(),
                hero.getId());
    }

    @Override
    public List<Hero> getHeroes() {
        final String SELECT_ALL_HEROES = "SELECT * FROM Hero";
        List<Hero> heroes = jdbc.query(SELECT_ALL_HEROES, new HeroMapper());
        return heroes;
    }

    @Override
    public void addHeroToOrganisation(int heroId, int organisationId){
        final String INSERT_HERO = "INSERT INTO OrganisationHero(HeroID, OrganisationID) "
                + "VALUES(?,?)";
        jdbc.update(INSERT_HERO, heroId, organisationId);
    }

    @Override
    public List<Organisation> getOrganisationsOfHero(int heroId) {
        final String SELECT_ALL_ORGANISATIONS = "SELECT * FROM OrganisationHero " +
                "INNER JOIN Organisation ON Organisation.OrganisationID = OrganisationHero.OrganisationID WHERE HeroID = ?";
        List<Organisation> organisations = jdbc.query(SELECT_ALL_ORGANISATIONS, new OrganisationDaoDB.OrganisationMapper(), heroId);
        return organisations;
    }

    //Handles the event where a user decides to create a hero with a power that doesn't exist yet
    private void handleNewSuperpower(String powerName){
        List<Superpower> superpowers = superpowerDao.getSuperpowers();
        for (Superpower superpower:superpowers){
            if(Objects.equals(superpower.getName(), powerName)){
                return;
            }
        }
        Superpower power = new Superpower();
        power.setName(powerName);
        superpowerDao.addSuperpower(power);
    }

    public static final class HeroMapper implements RowMapper<Hero> {

        @Override
        public Hero mapRow(ResultSet rs, int index) throws SQLException {
            Hero hero = new Hero();
            hero.setId(rs.getInt("HeroID"));
            hero.setName(rs.getString("Name"));
            hero.setDescription(rs.getString("Description"));
            hero.setSuperpower(rs.getString("Superpower"));
            return hero;
        }
    }
}
