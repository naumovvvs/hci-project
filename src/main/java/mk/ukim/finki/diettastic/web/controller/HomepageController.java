package mk.ukim.finki.diettastic.web.controller;

import mk.ukim.finki.diettastic.model.Meal;
import mk.ukim.finki.diettastic.model.Post;
import mk.ukim.finki.diettastic.service.MealService;
import mk.ukim.finki.diettastic.service.PostService;
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
    private final PostService postService;

    public HomepageController(MealService mealService, PostService postService) {
        this.mealService = mealService;
        this.postService = postService;
    }

    @GetMapping
    public String getHomePage(Model model) {
        List<Post> allPosts = this.postService.getAllPosts();

        if(allPosts.isEmpty() || allPosts.size() == 0) {
            allPosts = null;
        }

        model.addAttribute("headTitle", "Diettastic");
        model.addAttribute("bodyContent", "homepage");
        model.addAttribute("posts", allPosts);
        model.addAttribute("style1", "homepage.css");
        model.addAttribute("style2", "header.css");
        model.addAttribute("style3", "button.css");

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

        this.postService.publishPost(title, type, recipe, location);

        return "redirect:/home";
    }
}
