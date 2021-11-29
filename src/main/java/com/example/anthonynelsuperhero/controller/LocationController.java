package com.example.anthonynelsuperhero.controller;

import com.example.anthonynelsuperhero.dao.LocationDao;
import com.example.anthonynelsuperhero.dto.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class LocationController {
    @Autowired
    LocationDao locationDao;

    @GetMapping("Locations")
    public String displayLocations(Model model){
        List<Location> locations = locationDao.getLocations();
        model.addAttribute("locations", locations);
        return "Locations";
    }

    @PostMapping("addLocation")
    public String addLocation(String name, String description, String postcode, String latitude, String longitude){
        Location location = new Location();
        location.setName(name);
        location.setDescription(description);
        location.setPostcode(postcode);
        location.setLatitude(Float.parseFloat(latitude));
        location.setLongitude(Float.parseFloat(longitude));
        locationDao.addLocation(location);
        return "redirect:/Locations";
    }

    @GetMapping("deleteLocation")
    public String deleteLocation(float latitude, float longitude){
        locationDao.deleteLocation(latitude, longitude);
        return "redirect:/Locations";
    }

    @GetMapping("editLocation")
    public String editLocation(float latitude, float longitude, Model model){
        Location locationToEdit = new Location();
        for (Location location: locationDao.getLocations()){
            if (location.getLatitude() == latitude && location.getLongitude() == longitude){
                locationToEdit = location;
            }
        }
        model.addAttribute("location", locationToEdit);
        return "editLocation";
    }

    @PostMapping("editLocation")
    public String performEditLocation(Location location){
        locationDao.updateLocation(location);
        return "redirect:/Locations";
    }
}
