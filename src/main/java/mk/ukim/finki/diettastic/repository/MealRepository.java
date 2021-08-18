package mk.ukim.finki.diettastic.repository;

import mk.ukim.finki.diettastic.model.Meal;
import mk.ukim.finki.diettastic.model.enums.DietType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
    List<Meal> getAllByMealType(DietType mealType);
    Optional<Meal> getMealByMealName(String mealName);
}
