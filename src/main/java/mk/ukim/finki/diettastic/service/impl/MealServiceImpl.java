package mk.ukim.finki.diettastic.service.impl;

import mk.ukim.finki.diettastic.model.Meal;
import mk.ukim.finki.diettastic.model.enums.DietType;
import mk.ukim.finki.diettastic.repository.MealRepository;
import mk.ukim.finki.diettastic.service.MealService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MealServiceImpl implements MealService {

    private final MealRepository mealRepository;

    public MealServiceImpl(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    @Override
    public List<Meal> getAllMealsByType(DietType type) {
        return mealRepository.getAllByMealType(type);
    }

    @Override
    public Meal getMealByName(String mealName) {
        Optional<Meal> meal = mealRepository.getMealByMealName(mealName);

        if (meal.isPresent()) {
            return meal.get();
        } else {
            // TODO: error page
            throw new RuntimeException("Cannot find meal with that name");
        }
    }

    @Override
    public List<Meal> getAllMeals() {
        return this.mealRepository.findAll();
    }
}
