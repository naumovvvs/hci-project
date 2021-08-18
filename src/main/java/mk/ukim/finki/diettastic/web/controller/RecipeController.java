package mk.ukim.finki.diettastic.web.controller;

import mk.ukim.finki.diettastic.model.Meal;
import mk.ukim.finki.diettastic.model.enums.DietType;
import mk.ukim.finki.diettastic.service.MealService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/recipes")
public class RecipeController {

    private final MealService mealService;

    public RecipeController(MealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping
    public String getRecipePage(Model model) {

        model.addAttribute("headTitle", "Diettastic - ALl Recipes");
        model.addAttribute("bodyContent", "recipes");
        model.addAttribute("style1", "recipes.css");
        model.addAttribute("style2", "header.css");

        return "master-template";
    }

    @GetMapping("/normal")
    public String getNormalRecipes(Model model) {
        List<Meal> normalRecipes = this.mealService.getAllMealsByType(DietType.NORMAL);

        model.addAttribute("headTitle", "Diettastic - Normal Recipes");
        model.addAttribute("bodyContent", "listOfRecipes");
        model.addAttribute("recipes", normalRecipes);
        model.addAttribute("style1", "listOfRecipes.css");
        model.addAttribute("style2", "header.css");

        return "master-template";
    }

    @GetMapping("/vegan")
    public String getVeganRecipes(Model model) {
        List<Meal> veganRecipes = this.mealService.getAllMealsByType(DietType.VEGAN);

        model.addAttribute("headTitle", "Diettastic - Vegan Recipes");
        model.addAttribute("bodyContent", "listOfRecipes");
        model.addAttribute("recipes", veganRecipes);
        model.addAttribute("style1", "listOfRecipes.css");
        model.addAttribute("style2", "header.css");

        return "master-template";
    }

    @GetMapping("/vegetarian")
    public String getVegetarianRecipes(Model model) {
        List<Meal> vegetarianRecipes = this.mealService.getAllMealsByType(DietType.VEGETARIAN);

        model.addAttribute("headTitle", "Diettastic - Vegetarian Recipes");
        model.addAttribute("bodyContent", "listOfRecipes");
        model.addAttribute("recipes", vegetarianRecipes);
        model.addAttribute("style1", "listOfRecipes.css");
        model.addAttribute("style2", "header.css");

        return "master-template";
    }

    @GetMapping("/{recipeName}")
    public String getRecipePage(@PathVariable String recipeName, Model model) {
        Meal recipe = this.mealService.getMealByName(recipeName);

        model.addAttribute("headTitle", "Diettastic - Recipe");
        model.addAttribute("bodyContent", "recipePage");
        model.addAttribute("recipe", recipe);
        model.addAttribute("style2", "header.css");

        return "master-template";
    }
}
