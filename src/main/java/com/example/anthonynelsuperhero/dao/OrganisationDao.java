package com.example.anthonynelsuperhero.dao;

import com.example.anthonynelsuperhero.dto.Hero;
import com.example.anthonynelsuperhero.dto.Organisation;

import java.util.List;

public interface OrganisationDao {
    public void addOrganisation(Organisation organisation);
    public void deleteOrganisation(int id);
    public void updateOrganisation(Organisation organisation);
    public List<Organisation> getOrganisations();
    public List<Hero> getHeroesOfOrganisation(int organisationId);
}
