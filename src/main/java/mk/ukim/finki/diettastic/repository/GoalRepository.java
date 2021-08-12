package mk.ukim.finki.diettastic.repository;

import mk.ukim.finki.diettastic.model.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {

}
