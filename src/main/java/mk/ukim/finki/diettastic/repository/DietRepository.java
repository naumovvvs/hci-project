package mk.ukim.finki.diettastic.repository;

import mk.ukim.finki.diettastic.model.Diet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DietRepository extends JpaRepository<Diet, Long> {
    Optional<Diet> findByDietName(String dietName);
}
