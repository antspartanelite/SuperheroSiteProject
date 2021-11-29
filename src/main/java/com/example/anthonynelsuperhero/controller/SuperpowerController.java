package com.example.anthonynelsuperhero.controller;

import com.example.anthonynelsuperhero.dao.SuperpowerDao;
import com.example.anthonynelsuperhero.dto.Superpower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Objects;

@Controller
public class SuperpowerController {
    @Autowired
    SuperpowerDao superpowerDao;

    @GetMapping("Superpowers")
    public String displaySuperpowers(Model model){
        List<Superpower> superpowers = superpowerDao.getSuperpowers();
        model.addAttribute("superpowers", superpowers);
        return "Superpowers";
    }

    @PostMapping("addSuperpower")
    public String addSuperpower(String name, String description){
        Superpower superpower = new Superpower();
        superpower.setName(name);
        superpower.setDescription(description);
        superpowerDao.addSuperpower(superpower);
        return "redirect:/Superpowers";
    }

    @GetMapping("deleteSuperpower")
    public String deleteSuperpower(String name){
        superpowerDao.deleteSuperpower(name);
        return "redirect:/Superpowers";
    }

    @GetMapping("editSuperpower")
    public String editSuperpower(String name, Model model){
        Superpower superpowerToEdit = new Superpower();
        for (Superpower superpower: superpowerDao.getSuperpowers()){
            if (Objects.equals(superpower.getName(), name)){
                superpowerToEdit = superpower;
            }
        }
        model.addAttribute("superpower", superpowerToEdit);
        return "editSuperpower";
    }

    @PostMapping("editSuperpower")
    public String performEditSuperpower(Superpower superpower){
        superpowerDao.updateSuperpower(superpower);
        return "redirect:/Superpowers";
    }
}
