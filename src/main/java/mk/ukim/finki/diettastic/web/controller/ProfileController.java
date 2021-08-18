package mk.ukim.finki.diettastic.web.controller;

import mk.ukim.finki.diettastic.model.User;
import mk.ukim.finki.diettastic.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
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

        model.addAttribute("headTitle", "Diettastic - Edit Profile");
        model.addAttribute("bodyContent", "editProfileDetails");
        model.addAttribute("user", user);
        model.addAttribute("style2", "header.css");

        return "master-template";
    }

    @GetMapping("/{username}")
    public String getProfileByName(@PathVariable String username, Model model) {
        Optional<User> user = this.userService.findByUsername(username);

        if (user.isPresent()) {
            model.addAttribute("headTitle", "Diettastic - Profile Dashboard");
            model.addAttribute("bodyContent", "profileDashboard");
            model.addAttribute("user", user);
            model.addAttribute("style2", "header.css");

            return "master-template";
        } else {
            // TODO: Make error page
            return "";
        }
    }
}
