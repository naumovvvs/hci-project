package mk.ukim.finki.diettastic.service.impl;

import mk.ukim.finki.diettastic.model.Meal;
import mk.ukim.finki.diettastic.model.Post;
import mk.ukim.finki.diettastic.model.User;
import mk.ukim.finki.diettastic.model.enums.DietType;
import mk.ukim.finki.diettastic.repository.PostRepository;
import mk.ukim.finki.diettastic.service.MealService;
import mk.ukim.finki.diettastic.service.PostService;
import mk.ukim.finki.diettastic.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserService userService;
    private final MealService mealService;

    public PostServiceImpl(PostRepository postRepository, UserService userService, MealService mealService) {
        this.postRepository = postRepository;
        this.userService = userService;
        this.mealService = mealService;
    }

    @Override
    public List<Post> getAllPosts() {
        return this.postRepository.findAll();
    }

    @Override
    public Post publishPost(String title, String type, String recipe, String location) {
        DietType dietType = DietType.OTHER;

        if(type.equals("normal")) {
            dietType = DietType.NORMAL;
        } else if (type.equals("vegan")) {
            dietType = DietType.VEGAN;
        } else {
            dietType = DietType.VEGETARIAN;
        }

        // TODO: For testing only
        User author = userService.findByUsername("test").get();
        Meal meal = mealService.getMealByName(recipe);

        return postRepository.save(new Post(title, location, author, meal));
    }
}
