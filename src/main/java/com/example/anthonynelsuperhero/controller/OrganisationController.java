package com.example.anthonynelsuperhero.controller;

import com.example.anthonynelsuperhero.dao.OrganisationDao;
import com.example.anthonynelsuperhero.dto.Organisation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class OrganisationController {
    @Autowired
    OrganisationDao organisationDao;

    @GetMapping("Organisations")
    public String displayOrganisations(Model model){
        List<Organisation> organisations = organisationDao.getOrganisations();
        model.addAttribute("organisations", organisations);
        return "Organisations";
    }

    @PostMapping("addOrganisation")
    public String addOrganisation(String name, String description, String postcode){
        Organisation organisation = new Organisation();
        organisation.setName(name);
        organisation.setDescription(description);
        organisation.setPostcode(postcode);
        organisationDao.addOrganisation(organisation);
        return "redirect:/Organisations";
    }

    @GetMapping("deleteOrganisation")
    public String deleteOrganisation(int id){
        organisationDao.deleteOrganisation(id);
        return "redirect:/Organisations";
    }

    @GetMapping("editOrganisation")
    public String editOrganisation(int id, Model model){
        Organisation organisationToEdit = new Organisation();
        for (Organisation organisation: organisationDao.getOrganisations()){
            if (organisation.getId() == id){
                organisationToEdit = organisation;
            }
        }
        model.addAttribute("organisation", organisationToEdit);
        return "editOrganisation";
    }

    @PostMapping("editOrganisation")
    public String performEditOrganisation(Organisation organisation){
        organisationDao.updateOrganisation(organisation);
        return "redirect:/Organisations";
    }
}
