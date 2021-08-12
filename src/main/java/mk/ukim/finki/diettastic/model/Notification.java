package mk.ukim.finki.diettastic.model;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Notification extends BaseEntity{

    private String notificationDescription;

    public Notification() {
    }

    public Notification(String notificationDescription) {
        this.notificationDescription = notificationDescription;
    }
}
