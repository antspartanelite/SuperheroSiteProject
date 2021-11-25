package com.example.anthonynelsuperhero.dao;

import com.example.anthonynelsuperhero.dto.Sighting;
import com.example.anthonynelsuperhero.dto.Superpower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SuperPowerDaoDB implements SuperpowerDao{

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public void addSuperpower(Superpower superpower) {
        final String INSERT_SUPERPOWER = "INSERT INTO Superpower(Name, Description) "
                + "VALUES(?,?)";
        jdbc.update(INSERT_SUPERPOWER,
                superpower.getName(),
                superpower.getDescription());
    }

    @Override
    public void deleteSuperpower(String powerName) {
        final String DELETE_SUPERPOWER = "DELETE FROM Superpower WHERE Name = ?";
        jdbc.update(DELETE_SUPERPOWER, powerName);
    }

    @Override
    public void updateSuperpower(Superpower superpower) {
        final String UPDATE_SUPERPOWER = "UPDATE Superpower SET Description = ? "
                + "WHERE Name = ?";
        jdbc.update(UPDATE_SUPERPOWER,
                superpower.getDescription(),
                superpower.getName());
    }

    @Override
    public List<Superpower> getSuperpowers() {
        final String SELECT_ALL_SUPERPOWERS = "SELECT * FROM Superpower";
        List<Superpower> superpowers = jdbc.query(SELECT_ALL_SUPERPOWERS, new SuperpowerMapper());
        return superpowers;
    }

    public static final class SuperpowerMapper implements RowMapper<Superpower> {
        @Override
        public Superpower mapRow(ResultSet rs, int index) throws SQLException {
            Superpower superpower = new Superpower();
            superpower.setName(rs.getString("Name"));
            superpower.setDescription(rs.getString("Description"));
            return superpower;
        }
    }
}
