package com.example.anthonynelsuperhero.dao;

import com.example.anthonynelsuperhero.dto.*;

import java.util.List;

public interface HeroDao {
    public void addHero(Hero hero);
    public void deleteHeroById(int id);
    public void updateHero(Hero hero);
    public List<Hero> getHeroes();
    public void addHeroToOrganisation(int heroId, int organisationId);
    public List<Organisation> getOrganisationsOfHero(int heroId);

}
