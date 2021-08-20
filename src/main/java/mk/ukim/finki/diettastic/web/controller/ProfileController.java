package mk.ukim.finki.diettastic.web.controller;

import mk.ukim.finki.diettastic.model.Diet;
import mk.ukim.finki.diettastic.model.User;
import mk.ukim.finki.diettastic.repository.UserRepository;
import mk.ukim.finki.diettastic.service.DietService;
import mk.ukim.finki.diettastic.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final UserService userService;
    private final DietService dietService;
    private final UserRepository userRepository;

    public ProfileController(UserService userService, DietService dietService, UserRepository userRepository) {
        this.userService = userService;
        this.dietService = dietService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String getProfilePage(Model model) {
        // TODO: For testing purposes only
        User user = userService.findByUsername("test").get();

        model.addAttribute("headTitle", "Diettastic - Profile Dashboard");
        model.addAttribute("bodyContent", "profileDashboard");
        model.addAttribute("user", user);
        model.addAttribute("style2", "header.css");

        return "master-template";
    }

    @GetMapping("/edit")
    public String getEditProfileDetails(Model model) {
        // TODO: For testing purposes only
        User user = userService.findByUsername("test").get();
        List<Diet> diets = dietService.getAllDiets();
        // remove current diet (duplicate)
        diets.remove(user.getDiet());

        model.addAttribute("headTitle", "Diettastic - Edit Profile");
        model.addAttribute("bodyContent", "editProfileDetails");
        model.addAttribute("user", user);
        model.addAttribute("diets", diets);
        model.addAttribute("style2", "header.css");

        return "master-template";
    }

    @GetMapping("/{username}")
    public String getProfileByName(@PathVariable String username, Model model) {
        Optional<User> user = this.userService.findByUsername(username);

        if (user.isPresent()) {
            model.addAttribute("headTitle", "Diettastic - Profile Dashboard");
            model.addAttribute("bodyContent", "profileDashboard");
            model.addAttribute("user", user.get());
            model.addAttribute("style2", "header.css");

            return "master-template";
        } else {
            return "redirect:/home";
        }
    }

    @GetMapping("/removeNutritionist")
    public String removeNutritionistForUser() {
        // TODO: For testing only
        Optional<User> optionalUser = this.userService.findByUsername("test");

        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setNutritionist(null);
            this.userRepository.save(user);
        }

        return "redirect:/profile/edit";
    }

    @PostMapping("/editInfo")
    public String editInfoForLoggedInUser(@RequestParam String diet, @RequestParam Float weight, @RequestParam Float height) {
        // TODO: For testing only
        Optional<User> optionalUser = this.userService.findByUsername("test");
        Optional<Diet> currentDiet = this.dietService.getDietByName(diet);

        if (optionalUser.isPresent() && currentDiet.isPresent()) {
            this.userService.updateUser(optionalUser.get(), currentDiet.get(), weight, height);
        }

        return "redirect:/profile/edit";
    }

    @GetMapping("/friends")
    public String getFriendsPage(Model model) {
        List<User> list = this.userService.getAllFriends();

        if (list==null || list.isEmpty()) {
            list = null;
        }

        model.addAttribute("headTitle", "Diettastic - Friends");
        model.addAttribute("bodyContent", "friends");
        model.addAttribute("style1", "friends.css");
        model.addAttribute("style2", "header.css");
        model.addAttribute("list", list);

        return "master-template";
    }
}
