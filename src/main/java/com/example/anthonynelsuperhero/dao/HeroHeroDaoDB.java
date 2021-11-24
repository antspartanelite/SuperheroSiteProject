package com.example.anthonynelsuperhero.dao;

import com.example.anthonynelsuperhero.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class HeroHeroDaoDB implements HeroDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public void addHero(Hero hero) {
        final String INSERT_HERO = "INSERT INTO Hero(Name, Description, Superpower) "
                + "VALUES(?,?,?)";
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
                + "Superpower = ? WHERE id = ?";
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
    public List<Organisation> getOrganisationsOfHero(int heroId) {
        final String SELECT_ALL_ORGANISATIONS = "SELECT * FROM OrganisationHero " +
                "INNER JOIN Organisation ON Organisation.OrganisationID = OrganisationHero.OrganisationID WHERE HeroID = ?";
        List<Organisation> organisations = jdbc.query(SELECT_ALL_ORGANISATIONS, new OrganisationDaoDB.OrganisationMapper(), heroId);
        return organisations;
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
