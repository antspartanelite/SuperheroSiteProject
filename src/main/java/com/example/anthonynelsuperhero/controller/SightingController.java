package com.example.anthonynelsuperhero.controller;

import com.example.anthonynelsuperhero.dao.SightingDao;
import com.example.anthonynelsuperhero.dto.Sighting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SightingController {
    @Autowired
    SightingDao sightingDao;

    @GetMapping("Sightings")
    public String displaySightings(Model model){
        List<Sighting> sightings = sightingDao.getSightings();
        model.addAttribute("sightings", sightings);
        return "Sightings";
    }

    @GetMapping()
    public String displayRecentSightings(Model model){
        List<Sighting> sightings = sightingDao.getRecentSightings();
        model.addAttribute("sightings", sightings);
        return "index";
    }

    @PostMapping("addSighting")
    public String addSighting(int heroId, String date, String latitude, String longitude){
        Sighting sighting = new Sighting();
        sighting.setLatitude(Float.parseFloat(latitude));
        sighting.setLongitude(Float.parseFloat(longitude));
        sighting.setHeroId(heroId);
        sighting.setDate(date);
        sightingDao.addSighting(sighting);
        return "redirect:/Sightings";
    }

    @GetMapping("deleteSighting")
    public String deleteSighting(int id){
        sightingDao.deleteSighting(id);
        return "redirect:/Sightings";
    }

    @GetMapping("editSighting")
    public String editSighting(int id, Model model){
        Sighting sightingToEdit = new Sighting();
        for (Sighting sighting: sightingDao.getSightings()){
            if (sighting.getSightingId() == id){
                sightingToEdit = sighting;
            }
        }
        model.addAttribute("sighting", sightingToEdit);
        return "editSighting";
    }

    @PostMapping("editSighting")
    public String performEditSighting(Sighting sighting){
        sightingDao.updateSighting(sighting);
        return "redirect:/Sightings";
    }
}
