package mk.ukim.finki.diettastic.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import java.util.List;

@Data
@Entity
public class Diet extends BaseEntity{

    private String dietName;
    private String dietDescription;
    private Integer dietDuration;
    private Integer numberOfUsers;
    private Float dietRating;

    @ManyToMany
    private List<Meal> dietMeals;

    public Diet() {
    }

    public Diet(String dietName, String dietDescription, Integer dietDuration) {
        this.dietName = dietName;
        this.dietDescription = dietDescription;
        this.dietDuration = dietDuration;
        this.numberOfUsers = 0;
        this.dietRating = 0.0f;
    }
}
