package mk.ukim.finki.diettastic.web.controller;

import mk.ukim.finki.diettastic.model.User;
import mk.ukim.finki.diettastic.model.enums.DietType;
import mk.ukim.finki.diettastic.model.enums.Role;
import mk.ukim.finki.diettastic.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false)String error, Model model) {
        if(error!=null && !error.isEmpty()){
            model.addAttribute("error", error);
        }

        // TODO: CHANGE this
        // TODO: Hide menu on login screen

        model.addAttribute("headTitle", "Diettastic - Register");
        model.addAttribute("bodyContent", "register");
        model.addAttribute("style1", "register.css");
        model.addAttribute("style2", "header.css");

        return "master-template";
    }

    @PostMapping
    public String register (@RequestParam String username, @RequestParam String password, @RequestParam String email,
                            @RequestParam String name, @RequestParam String surname, @RequestParam String birthday,
                            @RequestParam(required = false) String file, @RequestParam Integer age,
                            @RequestParam Integer height, @RequestParam Integer weight, @RequestParam DietType dietType,
                            @RequestParam Role role, @RequestParam List<String> goals,
                            HttpServletRequest request) {


        this.userService.register(username, password, email, name, surname, birthday, file, age,
                height, weight, dietType, role, goals);

        Optional<User> user = this.userService.login(username, password);

        request.getSession().setAttribute("user", user.get());

        // TODO: add loading screen
        return "redirect:/register/diet";
    }

    @GetMapping("/diet")
    public String getFinalDietPage(@RequestParam(required = false)String error, Model model) {
        if (error!=null && !error.isEmpty()) {
            model.addAttribute("error", error);
        }

        // TODO: CHANGE this
        // TODO: Hide menu on login screen

        model.addAttribute("headTitle", "Diettastic - Register");
        model.addAttribute("bodyContent", "diet");
        model.addAttribute("style1", "diet.css");
        model.addAttribute("style2", "header.css");

        return "master-template";
    }

    @PostMapping("/diet")
    public String postDecision(@RequestParam String decision) {
        // TODO: Redirect to Profile Dashboard instead
        // TODO: Make logic for choosing a diet and save it to the user
        return "redirect:/home";
    }
}
