package com.example.anthonynelsuperhero;

import com.example.anthonynelsuperhero.dao.SuperpowerDao;
import com.example.anthonynelsuperhero.dto.Superpower;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SuperpowerDaoDBTests {

    @Autowired
    SuperpowerDao superpowerDao;

    public SuperpowerDaoDBTests(){}

    //Clears the relevant set of tables for these tests
    @BeforeEach
    public void setUp() {
        List<Superpower> superpowers = superpowerDao.getSuperpowers();
        for (Superpower superpower : superpowers) {
            superpowerDao.deleteSuperpower(superpower.getName());
        }
    }

    @Test
    public void addAndGetSuperpowersTest(){
        Superpower superpowerToAdd = new Superpower();
        superpowerToAdd.setName("Money");
        superpowerToAdd.setDescription("The user of this power is extremely wealthy");
        superpowerDao.addSuperpower(superpowerToAdd);

        superpowerToAdd.setName("Strength");
        superpowerToAdd.setDescription("The user of this power has the strength to lift up large objects");
        superpowerDao.addSuperpower(superpowerToAdd);

        superpowerToAdd.setName("Flight");
        superpowerToAdd.setDescription("The user of this power can fly");
        superpowerDao.addSuperpower(superpowerToAdd);

        List<Superpower> superpowers = superpowerDao.getSuperpowers();

        assertEquals("Money", superpowers.get(1).getName());
        assertEquals("The user of this power is extremely wealthy", superpowers.get(1).getDescription());

        assertEquals("Strength", superpowers.get(2).getName());
        assertEquals("The user of this power has the strength to lift up large objects", superpowers.get(2).getDescription());

        assertEquals("Flight", superpowers.get(0).getName());
        assertEquals("The user of this power can fly", superpowers.get(0).getDescription());
    }

    @Test
    public void deleteSuperpowerTest(){
        Superpower superpowerToAdd = new Superpower();
        superpowerToAdd.setName("Money");
        superpowerToAdd.setDescription("The user of this power is extremely wealthy");
        superpowerDao.addSuperpower(superpowerToAdd);

        superpowerToAdd.setName("Strength");
        superpowerToAdd.setDescription("The user of this power has the strength to lift up large objects");
        superpowerDao.addSuperpower(superpowerToAdd);

        superpowerToAdd.setName("Flight");
        superpowerToAdd.setDescription("The user of this power can fly");
        superpowerDao.addSuperpower(superpowerToAdd);

        List<Superpower> superpowers = superpowerDao.getSuperpowers();

        superpowerDao.deleteSuperpower(superpowers.get(1).getName());

        superpowers = superpowerDao.getSuperpowers();

        assertEquals("Strength", superpowers.get(1).getName());
        assertEquals("The user of this power has the strength to lift up large objects", superpowers.get(1).getDescription());

        assertEquals("Flight", superpowers.get(0).getName());
        assertEquals("The user of this power can fly", superpowers.get(0).getDescription());

        assertEquals(2, superpowers.size());
    }

    @Test
    public void updateSuperpowerTest() {
        Superpower superpowerToAdd = new Superpower();
        superpowerToAdd.setName("Money");
        superpowerToAdd.setDescription("The user of this power is extremely wealthy");
        superpowerDao.addSuperpower(superpowerToAdd);

        superpowerToAdd.setName("Strength");
        superpowerToAdd.setDescription("The user of this power has the strength to lift up large objects");
        superpowerDao.addSuperpower(superpowerToAdd);

        superpowerToAdd.setName("Flight");
        superpowerToAdd.setDescription("The user of this power can fly");
        superpowerDao.addSuperpower(superpowerToAdd);

        superpowerToAdd.setName("Strength");
        superpowerToAdd.setDescription("The user of this power can punch very hard");

        superpowerDao.updateSuperpower(superpowerToAdd);

        List<Superpower> superpowers = superpowerDao.getSuperpowers();

        assertEquals("Money", superpowers.get(1).getName());
        assertEquals("The user of this power is extremely wealthy", superpowers.get(1).getDescription());

        assertEquals("Strength", superpowers.get(2).getName());
        assertEquals("The user of this power can punch very hard", superpowers.get(2).getDescription());

        assertEquals("Flight", superpowers.get(0).getName());
        assertEquals("The user of this power can fly", superpowers.get(0).getDescription());
    }

}
