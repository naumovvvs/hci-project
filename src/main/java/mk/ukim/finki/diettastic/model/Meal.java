package mk.ukim.finki.diettastic.model;

import lombok.Data;
import mk.ukim.finki.diettastic.model.enums.DietType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Entity
public class Meal extends BaseEntity{

    @Enumerated(value = EnumType.STRING)
    private DietType mealType;

    private String mealName;
    private Float healthyScore;
    private String ingredients;
    private String instructions;

    public Meal() {
    }

    public Meal(String mealName, DietType mealType, Float healthyScore, String ingredients, String instructions) {
        this.mealName = mealName;
        this.mealType = mealType;
        this.healthyScore = healthyScore;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }
}
