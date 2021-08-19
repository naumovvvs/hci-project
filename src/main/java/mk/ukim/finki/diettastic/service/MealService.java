package mk.ukim.finki.diettastic.service;

import mk.ukim.finki.diettastic.model.Meal;
import mk.ukim.finki.diettastic.model.enums.DietType;

import java.util.List;

public interface MealService {
    List<Meal> getAllMealsByType(DietType type);
    List<Meal> getAllMeals();
    Meal getMealByName(String mealName);
}
