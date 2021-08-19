package mk.ukim.finki.diettastic.web.controller;

import mk.ukim.finki.diettastic.model.Diet;
import mk.ukim.finki.diettastic.model.User;
import mk.ukim.finki.diettastic.repository.DietRepository;
import mk.ukim.finki.diettastic.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.util.Optional;

@Controller
@RequestMapping("/diet")
public class DietController {

    private final UserService userService;
    private final DietRepository dietRepository;

    public DietController(UserService userService, DietRepository dietRepository) {
        this.userService = userService;
        this.dietRepository = dietRepository;
    }

    @GetMapping("/{dietName}")
    public String getDietPageByName(@PathVariable String dietName, Model model) {
        Optional<Diet> optionalDiet = dietRepository.findByDietName(dietName);
        DecimalFormat df = new DecimalFormat("###.#");


        if (optionalDiet.isPresent()) {
            // TODO: add code
            Diet diet = optionalDiet.get();
            diet.setDietRating((Float.parseFloat(df.format(diet.getDietRating()))));

            model.addAttribute("headTitle", "Diettastic - Diet");
            model.addAttribute("bodyContent", "dietPage");
            model.addAttribute("diet", diet);
            model.addAttribute("style2", "header.css");
            return "master-template";
        } else {
            // TODO: Make error page
            return "";
        }
    }

    @GetMapping("/myDiet")
    public String getMyDietPage(HttpServletRequest request, Model model) {
        // User user = (User) request.getSession().getAttribute("user");

        // TODO: For testing purposes only. REMOVE FOR PRODUCTION VERSION!
        User user = userService.findByUsername("test").get();

        model.addAttribute("headTitle", "Diettastic - My Diet");
        model.addAttribute("bodyContent", "myDiet");
        model.addAttribute("user", user);
        model.addAttribute("style2", "header.css");

        return "master-template";
    }

    @GetMapping("/changeDiet")
    public String getChangeDietPage(Model model) {
        // User user = (User) request.getSession().getAttribute("user");

        // TODO: For testing purposes only. REMOVE FOR PRODUCTION VERSION!
        User user = userService.findByUsername("test").get();

        model.addAttribute("headTitle", "Diettastic - Change Diet");
        model.addAttribute("bodyContent", "changeDiet");
        model.addAttribute("user", user);
        model.addAttribute("style1", "changeDiet.css");
        model.addAttribute("style2", "header.css");

        return "master-template";
    }
}
