package mk.ukim.finki.diettastic.web.controller;

import mk.ukim.finki.diettastic.model.Diet;
import mk.ukim.finki.diettastic.model.Meal;
import mk.ukim.finki.diettastic.model.User;
import mk.ukim.finki.diettastic.service.DietService;
import mk.ukim.finki.diettastic.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/diet")
public class DietController {

    private final UserService userService;
    private final DietService dietService;

    public DietController(UserService userService, DietService dietService) {
        this.userService = userService;
        this.dietService = dietService;
    }

    @GetMapping("/{dietName}")
    public String getDietPageByName(@PathVariable String dietName, Model model) {
        Optional<Diet> optionalDiet = dietService.getDietByName(dietName);
        DecimalFormat df = new DecimalFormat("###.#");


        if (optionalDiet.isPresent()) {
            Diet diet = optionalDiet.get();
            diet.setDietRating((Float.parseFloat(df.format(diet.getDietRating()))));

            List<String> description = Arrays.asList(diet.getDietDescription().split("/"));
            List<String> meals = diet.getDietMeals().stream().map(Meal::getMealName).collect(Collectors.toList());

            model.addAttribute("headTitle", "Diettastic - Diet");
            model.addAttribute("bodyContent", "dietPage");
            model.addAttribute("diet", diet);
            model.addAttribute("description", description);
            model.addAttribute("meals", meals);
            model.addAttribute("style2", "header.css");
            return "master-template";
        } else {
            return "redirect:/home";
        }
    }

    @GetMapping("/myDiet")
    public String getMyDietPage(HttpServletRequest request, Model model) {
        // User user = (User) request.getSession().getAttribute("user");

        // TODO: For testing purposes only
        User user = userService.findByUsername("test").get();
        List<String> description = Arrays.asList(user.getDiet().getDietDescription().split("/"));

        model.addAttribute("headTitle", "Diettastic - My Diet");
        model.addAttribute("bodyContent", "myDiet");
        model.addAttribute("user", user);
        model.addAttribute("description", description);
        model.addAttribute("style2", "header.css");

        return "master-template";
    }

    @GetMapping("/changeDiet")
    public String getChangeDietPage(Model model) {
        // User user = (User) request.getSession().getAttribute("user");

        // TODO: For testing purposes only
        User user = userService.findByUsername("test").get();

        model.addAttribute("headTitle", "Diettastic - Change Diet");
        model.addAttribute("bodyContent", "changeDiet");
        model.addAttribute("user", user);
        model.addAttribute("style1", "changeDiet.css");
        model.addAttribute("style2", "header.css");

        return "master-template";
    }

    @PostMapping("findDiet")
    public String findDiet() {
        return "redirect:/register/diet";
    }

    @PostMapping("/tryDiet")
    public String tryDiet(@RequestParam String dietName) {
        this.dietService.changeDiet(dietName);

        return "redirect:/diet/myDiet";
    }
}
