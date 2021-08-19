package mk.ukim.finki.diettastic;

import mk.ukim.finki.diettastic.model.Diet;
import mk.ukim.finki.diettastic.model.Goal;
import mk.ukim.finki.diettastic.model.Meal;
import mk.ukim.finki.diettastic.model.User;
import mk.ukim.finki.diettastic.model.enums.DietType;
import mk.ukim.finki.diettastic.model.enums.Role;
import mk.ukim.finki.diettastic.repository.DietRepository;
import mk.ukim.finki.diettastic.repository.GoalRepository;
import mk.ukim.finki.diettastic.repository.MealRepository;
import mk.ukim.finki.diettastic.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DiettasticApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext =
                SpringApplication.run(DiettasticApplication.class, args);

        UserRepository userRepository = configurableApplicationContext.getBean(UserRepository.class);
        GoalRepository goalRepository = configurableApplicationContext.getBean(GoalRepository.class);
        DietRepository dietRepository = configurableApplicationContext.getBean(DietRepository.class);
        MealRepository mealRepository = configurableApplicationContext.getBean(MealRepository.class);

        // =============================================================================================================
        // Diets and Meals
        Meal meal1 = new Meal("Chai Spice Cheesecake", DietType.VEGAN, 7.5f, "1 tspn Ginger/2 tspns Chai Spice Mix/100g brown sugar/1oz Heavy Cream/2 eggs/2 tbsp dry milk",  "Blend cheesecake-batter ingredients/Pour in batter/Bake until top is lightly puffed/Cool to room temperature", "https://images.eatsmarter.com/sites/default/files/styles/max_size/public/chai-spiced-cheesecake-532130.jpg", "https://www.mymoderncookery.com/wp-content/uploads/2015/11/Lara-bar-7-of-7-1024x678.jpg");
        Meal meal2 = new Meal("Vegan Tomato Soup", DietType.VEGAN, 9.0f, "1 tspn Olive oil/800g tomatoes/1 cup water/1 tbsp paprika/2 eggs/salt and peper",  "Heat olive oil in a pot over low heat/Add garlic and cook until fragrant/Bring to a boil, reduce heat, and simmer until tomatoes have broken down and soup starts to thicken./Puree tomato soup/Reheat soup before serving", "https://www.blissfulbasil.com/wp-content/uploads/2014/01/Dreamy-Vegan-Tomato-Soup-7011-500x500.jpg", "https://avirtualvegan.com/wp-content/uploads/2015/11/tomato-basil-soup-3-resized-3.jpg");

        Diet diet1 = new Diet("Dukan diet", "The Dukan diet is a high-protein, low-carb weight loss diet split into four phases./The weight loss phases are primarily based on eating unlimited high-protein foods and mandatory oat bran.", 60);
        Diet diet2 = new Diet("Vegan diet", "The vegan diet restricts all animal products for ethical, environmental, or health reasons./Veganism is also associated with resistance to animal exploitation and cruelty./Veganism is the strictest form of vegetarianism.", 50);

        List<Meal> mealsList = new ArrayList<>();

        mealsList.add(meal1);
        mealsList.add(meal2);

        diet1.setDietMeals(new ArrayList<>());
        diet2.setDietMeals(mealsList);

        mealRepository.save(meal1);
        mealRepository.save(meal2);

        dietRepository.save(diet1);
        dietRepository.save(diet2);

        // =============================================================================================================

        Diet diet = new Diet("Keto Diet", "Description of Keto Diet", 69);
        dietRepository.save(diet);

        List<Goal> goals = new ArrayList<>();
        Goal goal = new Goal("Be healthier", "/");
        goals.add(goal);

        goalRepository.save(goal);

        User user = new User("test", "test", "test@mail.com", "John",
                "Smith", "2021-01-01", "/", 21, 172.0F, 80.0F,
                DietType.NORMAL, Role.ROLE_BASIC, goals);

        User nutritionist = new User("testN", "test", "testN@mail.com", "Alex",
                "James", "2021-02-03", "/", 25, 180.0F, 69.0F,
                DietType.NORMAL, Role.ROLE_NUTRITIONIST, goals);


        user.setDiet(diet);
        user.setNutritionist(nutritionist);

        userRepository.save(nutritionist);
        userRepository.save(user);


        //SpringApplication.run(DiettasticApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

}
