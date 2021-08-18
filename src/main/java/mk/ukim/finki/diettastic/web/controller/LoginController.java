package mk.ukim.finki.diettastic.web.controller;

import mk.ukim.finki.diettastic.model.User;
import mk.ukim.finki.diettastic.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getIndexPage(@RequestParam(required = false)String error, Model model){
        if(error!=null && !error.isEmpty()){
            model.addAttribute("error", error);
        }

        model.addAttribute("headTitle", "Diettastic");
        model.addAttribute("bodyContent", "index");
        model.addAttribute("style1", "index.css");
        model.addAttribute("style2", "header.css");

        return "master-template";
    }

    @PostMapping
    public String login(@RequestParam String username, @RequestParam String password,
                        HttpServletRequest request, Model model) {

        Optional<User> user = Optional.empty();

        try {
            user = this.userService.login(username, password);
        }catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("headTitle", "Diettastic - Login");
            model.addAttribute("style2", "header.css");
            model.addAttribute("bodyContent", "login");

            return "master-template";
        }

        if (user.isPresent()) {
            request.getSession().setAttribute("user", user.get());
            return "redirect:/home";
        } else {
            return "redirect:/login";
        }
    }
}
