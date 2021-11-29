package com.example.anthonynelsuperhero.controller;

import com.example.anthonynelsuperhero.dao.HeroDao;
import com.example.anthonynelsuperhero.dao.HeroDaoDB;
import com.example.anthonynelsuperhero.dto.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HeroController {

    @Autowired
    HeroDao heroDao;

    @GetMapping("Heroes")
    public String displayHeroes(Model model){
        List<Hero> heroes = heroDao.getHeroes();
        model.addAttribute("heroes", heroes);
        return "Heroes";
    }

    @PostMapping("addHero")
    public String addHero(String name, String description, String superpower){
        Hero hero = new Hero();
        hero.setName(name);
        hero.setDescription(description);
        hero.setSuperpower(superpower);
        heroDao.addHero(hero);
        return "redirect:/Heroes";
    }

    @GetMapping("deleteHero")
    public String deleteHero(int id){
        heroDao.deleteHeroById(id);
        return "redirect:/Heroes";
    }

    @GetMapping("editHero")
    public String editHero(int id, Model model){
        Hero heroToEdit = new Hero();
        for (Hero hero: heroDao.getHeroes()){
            if (hero.getId() == id){
                heroToEdit = hero;
            }
        }
        model.addAttribute("hero", heroToEdit);
        return "editHero";
    }

    @PostMapping("editHero")
    public String performEditHero(Hero hero){
        heroDao.updateHero(hero);
        return "redirect:/Heroes";
    }
}
