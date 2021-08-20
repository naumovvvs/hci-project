package mk.ukim.finki.diettastic.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;

@Data
@Entity
public class Post extends BaseEntity {

    private String postName;
    private LocalDateTime postTimestamp;
    private String location;

    @ManyToOne
    private User author;

    @ManyToOne
    private Meal meal;

    public Post() {

    }

    public Post(String postName, String location, User author, Meal meal) {
        this.postName = postName;
        this.location = location;
        this.author = author;
        this.meal = meal;
        this.postTimestamp = LocalDateTime.now();
    }
}
