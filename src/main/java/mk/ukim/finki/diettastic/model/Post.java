package mk.ukim.finki.diettastic.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@Entity
public class Post extends BaseEntity {

    private String postName;
    private LocalDateTime postTimestamp;
    private String location;
    private Float healthyScore;

    @ManyToOne
    private User author;

    // TODO: List of images (byte [])

    public Post() {

    }

    public Post(String postName, String location, Float healthyScore, User author) {
        this.postName = postName;
        this.location = location;
        this.healthyScore = healthyScore;
        this.author = author;
        this.postTimestamp = LocalDateTime.now();
    }
}
