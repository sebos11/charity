package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.InstitutionRepository;

import javax.persistence.criteria.CriteriaBuilder;


@Controller
public class InstitutionController {

private final InstitutionRepository institutionRepository;

public InstitutionController(InstitutionRepository institutionRepository){
    this.institutionRepository = institutionRepository;
}



    @RequestMapping("/inst")
    public String institutionList(Model model){
        Iterable<Institution> institutions= institutionRepository.findAll();

        model.addAttribute("institutions",institutions);

        return "index";
    }
}