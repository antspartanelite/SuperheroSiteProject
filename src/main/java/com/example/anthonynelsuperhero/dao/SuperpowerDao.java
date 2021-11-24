package com.example.anthonynelsuperhero.dao;

import com.example.anthonynelsuperhero.dto.Superpower;

public interface SuperpowerDao {
    public void addSuperpower(Superpower superpower);
    public void deleteSuperpower(String powerName);
    public void updateSuperpower(Superpower superpower);
    public Superpower getSuperpower(String powerName);
}
