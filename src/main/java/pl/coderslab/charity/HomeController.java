package pl.coderslab.charity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;

import java.util.List;


@Controller
public class HomeController {
    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;

    public HomeController(InstitutionRepository institutionRepository, DonationRepository donationRepository){
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
    }

    @RequestMapping("/")
    public String homeAction(Model model){
        List<Institution> institutions= institutionRepository.findAll();
        List<Donation> donations = donationRepository.findAll();
        Integer quantity =0;
        long donationQuantity = donations.size();
        for (Donation donation:donations) {

            quantity = quantity + donation.getQuantity();
        }

        model.addAttribute("institutions",institutions);
        model.addAttribute("donationQuantity",donationQuantity);
        model.addAttribute("quantity", quantity);



        return "index";
    }
}
