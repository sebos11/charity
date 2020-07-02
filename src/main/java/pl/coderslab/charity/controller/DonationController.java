package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DonationController {
    private final CategoryRepository categoryRepository;
    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;

    public DonationController(CategoryRepository categoryRepository, InstitutionRepository institutionRepository,
                                DonationRepository donationRepository ){
        this.categoryRepository = categoryRepository;
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
    }

    @RequestMapping("/form")
    public String step1Form(Model model){
        model.addAttribute("donation", new Donation());

        return "form";
    }

    @PostMapping("/formconf")
    public String formConfirmation(@RequestParam Integer bags, @RequestParam List<Long> categories,
                                   @RequestParam Long organization,
                                   @RequestParam String address, @RequestParam String city,
                                   @RequestParam String postcode, @RequestParam String phone,
                                   @RequestParam String data, @RequestParam String time,
                                   @RequestParam String more_info
       ){

        LocalDate dateDonation = LocalDate.parse(data);
        LocalTime timeDonation = LocalTime.parse(time);
        List<Category> checkedCategories = new ArrayList<>();
        if (categories == null) { return "form";}

        for (Long v : categories) {
             checkedCategories.add(categoryRepository.findById(v).get());
        }


        Institution institution = new Institution();
        if (organization == null) { return "form";}
        institution = institutionRepository.findById(organization).get();


        Donation donation = new Donation();
        donation.setCategories(checkedCategories);
        donation.setInstitution(institution);
        donation.setQuantity(bags);
        donation.setStreet(address);
        donation.setCity(city);
        donation.setZipCode(postcode);
        donation.setPickUpDate(dateDonation);
        donation.setPickUpTime(timeDonation);
        donation.setPickUpComment(more_info);
        donationRepository.save(donation);











        return "form-confirmation";
    }

    @ModelAttribute("categories")
    public List<Category> categories() {
        return categoryRepository.findAll();
    }

    @ModelAttribute("institutions")
    public List<Institution> institutionsList() {
        return institutionRepository.findAll();
    }
}
