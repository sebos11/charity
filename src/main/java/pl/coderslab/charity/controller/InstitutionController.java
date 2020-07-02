package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.InstitutionRepository;

import java.util.List;


@Controller
public class InstitutionController {

private final InstitutionRepository institutionRepository;

public InstitutionController(InstitutionRepository institutionRepository){
    this.institutionRepository = institutionRepository;
}



    @RequestMapping("/inst")
    public String institutionList(Model model){
        List<Institution> institutions= institutionRepository.findAll();

        model.addAttribute("institutions",institutions);

        return "index";
    }
}