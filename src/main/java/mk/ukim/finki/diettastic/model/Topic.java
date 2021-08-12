package mk.ukim.finki.diettastic.model;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Topic extends BaseEntity{

    private String topicName;
    private String topicDescription;

    // TODO: List of images (byte [])

    public Topic() {
    }

    public Topic(String topicName, String topicDescription) {
        this.topicName = topicName;
        this.topicDescription = topicDescription;
    }
}
