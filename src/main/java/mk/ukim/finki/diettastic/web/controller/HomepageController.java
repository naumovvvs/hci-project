package mk.ukim.finki.diettastic.web.controller;

import mk.ukim.finki.diettastic.model.Meal;
import mk.ukim.finki.diettastic.service.MealService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomepageController {

    private final MealService mealService;

    public HomepageController(MealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping
    public String getHomePage(Model model) {
        model.addAttribute("headTitle", "Diettastic");
        model.addAttribute("bodyContent", "homepage");
        model.addAttribute("style1", "homepage.css");
        model.addAttribute("style2", "header.css");

        return "master-template";
    }

    @GetMapping("/notifications")
    public String getNotificationsPage(Model model) {
        model.addAttribute("headTitle", "Diettastic");
        model.addAttribute("bodyContent", "notifications");
        model.addAttribute("style2", "header.css");

        return "master-template";
    }

    @GetMapping("/addPost")
    public String getAddPostPage(Model model) {
        List<Meal> recipes = this.mealService.getAllMeals();

        model.addAttribute("headTitle", "Diettastic");
        model.addAttribute("bodyContent", "addPost");
        model.addAttribute("recipes", recipes);
        model.addAttribute("style2", "header.css");

        return "master-template";
    }

    @PostMapping("/publishPost")
    public String publishPost(@RequestParam String title, @RequestParam String type,
                              @RequestParam String recipe, @RequestParam String location) {

        // TODO: Finish logic

        return "redirect:/home";
    }
}
