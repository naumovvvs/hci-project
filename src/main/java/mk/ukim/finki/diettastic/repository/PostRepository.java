package mk.ukim.finki.diettastic.repository;

import mk.ukim.finki.diettastic.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
