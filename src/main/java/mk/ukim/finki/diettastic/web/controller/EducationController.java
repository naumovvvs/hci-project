package mk.ukim.finki.diettastic.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/education")
public class EducationController {

    @GetMapping
    public String getEducationSection(Model model) {
        model.addAttribute("headTitle", "Diettastic - Education");
        model.addAttribute("bodyContent", "educationSection");
        model.addAttribute("style2", "header.css");

        return "master-template";
    }

    // TODO: Three methods for each topic
    @GetMapping("/weightLoss")
    public String getWeightLossPage(Model model) {
        model.addAttribute("headTitle", "Diettastic - Weight Loss");
        model.addAttribute("bodyContent", "topicPage");
        model.addAttribute("style1", "topicPage.css");
        model.addAttribute("style2", "header.css");

        return "master-template";
    }

    // TODO: Three methods for each topic
    @GetMapping("/gainingMuscle")
    public String getGainMusclepage(Model model) {
        model.addAttribute("headTitle", "Diettastic - Gaining Muscle");
        model.addAttribute("bodyContent", "topicPage");
        model.addAttribute("style1", "topicPage.css");
        model.addAttribute("style2", "header.css");

        return "master-template";
    }

    // TODO: Three methods for each topic
    @GetMapping("/goVegan")
    public String getGoVeganPage(Model model) {
        model.addAttribute("headTitle", "Diettastic - Why go vegan?");
        model.addAttribute("bodyContent", "topicPage");
        model.addAttribute("style1", "topicPage.css");
        model.addAttribute("style2", "header.css");

        return "master-template";
    }

}
