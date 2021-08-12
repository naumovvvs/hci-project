package mk.ukim.finki.diettastic.repository;

import mk.ukim.finki.diettastic.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
