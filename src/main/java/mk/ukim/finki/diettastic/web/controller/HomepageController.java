package mk.ukim.finki.diettastic.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomepageController {

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
}
