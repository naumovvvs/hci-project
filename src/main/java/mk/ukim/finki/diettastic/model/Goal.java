package mk.ukim.finki.diettastic.model;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Goal extends BaseEntity{

    private String goalName;
    private String goalDescription;

    public Goal() {
    }

    public Goal(String goalName) {
        this.goalName = goalName;
    }

    public Goal(String goalName, String goalDescription) {
        this.goalName = goalName;
        this.goalDescription = goalDescription;
    }
}
