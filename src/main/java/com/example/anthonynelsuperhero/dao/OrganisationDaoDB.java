package com.example.anthonynelsuperhero.dao;

import com.example.anthonynelsuperhero.dto.Hero;
import com.example.anthonynelsuperhero.dto.Organisation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OrganisationDaoDB implements OrganisationDao{

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public void addOrganisation(Organisation organisation) {
        final String INSERT_ORGANISATION = "INSERT INTO Organisation(OrganisationID, Name, Description, Postcode) "
                + "VALUES(?,?,?,?)";
        jdbc.update(INSERT_ORGANISATION,
                organisation.getId(),
                organisation.getName(),
                organisation.getDescription(),
                organisation.getPostcode());
    }

    @Override
    public void deleteOrganisation(int id) {
        final String DELETE_HERO = "DELETE FROM Organisation WHERE OrganisationID = ?";
        jdbc.update(DELETE_HERO, id);
    }

    @Override
    public void updateOrganisation(Organisation organisation) {
        final String UPDATE_ORGANISATION = "UPDATE Organisation SET Name = ?, Description = ? "
                + ", Postcode = ? WHERE OrganisationID = ?";
        jdbc.update(UPDATE_ORGANISATION,
                organisation.getName(),
                organisation.getDescription(),
                organisation.getPostcode(),
                organisation.getId());
    }

    @Override
    public List<Organisation> getOrganisations() {
        final String SELECT_ALL_ORGANISATIONS = "SELECT * FROM Organisation";
        List<Organisation> organisations = jdbc.query(SELECT_ALL_ORGANISATIONS, new OrganisationMapper());
        return organisations;
    }


    @Override
    public List<Hero> getHeroesOfOrganisation(int organisationId) {
        final String SELECT_ALL_HEROES = "SELECT * FROM OrganisationHero " +
                "INNER JOIN Hero ON Hero.HeroID = OrganisationHero.HeroID WHERE OrganisationID = ?";
        List<Hero> heroes = jdbc.query(SELECT_ALL_HEROES, new HeroDaoDB.HeroMapper(), organisationId);
        return heroes;
    }

    public static final class OrganisationMapper implements RowMapper<Organisation> {
        @Override
        public Organisation mapRow(ResultSet rs, int index) throws SQLException {
            Organisation organisation = new Organisation();
            organisation.setId(rs.getInt("OrganisationID"));
            organisation.setName(rs.getString("Name"));
            organisation.setDescription(rs.getString("Description"));
            organisation.setPostcode(rs.getString("Postcode"));
            return organisation;
        }
    }
}
